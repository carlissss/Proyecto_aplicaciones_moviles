package com.example.a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Principal : AppCompatActivity() {

    private lateinit var txt1: EditText
    private lateinit var txt2: EditText
    private lateinit var btn1: Button
    private lateinit var btn2: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        txt1 = findViewById(R. id.txt1)
        txt2 = findViewById(R.id.txt2)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)


        botonLogin()
        botonCrearCuenta()
        desactivarFlecha()




    }//Cierra el onCreate

    fun botonLogin(){
        btn1.setOnClickListener {
            if(txt1.text.toString().trim().isEmpty() || txt2.text.toString().trim().isEmpty()){
                Toast.makeText(
                    this@Principal,
                    "!Complete los campos Faltantes¡", Toast.LENGTH_SHORT
                ).show()

            }else{
                var rut = txt1.text.toString().trim().uppercase()
                var con = txt2.text.toString().toInt()

                var nom = ""
                var ape = ""
                var ema = ""
                var tip = ""
                var rep = 0 // Variable para el campo de repetir contraseña
                var estado = false

                var dbref = FirebaseDatabase.getInstance().getReference("Usuario")

                dbref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(x in snapshot.children){
                            if(rut.equals(x.child("rut").getValue().toString(), ignoreCase = true) && con == x.child("contraseña").getValue().toString().toInt()){
                                estado = true
                                nom = x.child("nombre").getValue().toString()
                                ape = x.child("apellido").getValue().toString()
                                ema = x.child("email").getValue().toString()
                                tip = x.child("tipo").getValue().toString()

                                // Intentamos leer repetirContraseña de Firebase, si no existe usamos la contraseña por defecto
                                val repVal = x.child("repetirContraseña").getValue()
                                rep = if (repVal != null) repVal.toString().toInt() else con

                                // Se le pasan los 7 parámetros requeridos por la clase Usuario
                                var usu = Usuario(nom, ape, ema, tip, con, rut, rep)
                                var intent = Intent(this@Principal, Menu_usu::class.java)
                                intent.putExtra("usu", usu)
                                startActivity(intent)

                            }// cierra el if

                        }// cierra el for

                        if(estado == false){
                            txt1.setText("")
                            txt2.setText("")

                            Toast.makeText(
                                this@Principal, "Error de usuario y/o contraseña", Toast.LENGTH_SHORT
                            ).show()

                        }

                    }// cierra el ondataChange

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })


            }

        }//cierra el if/else

    }//cierra el fun botonLogin

    fun botonCrearCuenta(){
        btn2.setOnClickListener {
            var intent = Intent(this@Principal, Crear_cuenta::class.java)
            startActivity(intent)
        }
    }//cierra el fun botonCrearCuenta

    fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {
            //finich()
        }
    }


}// Cierra la clase Principal