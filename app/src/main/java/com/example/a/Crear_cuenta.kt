package com.example.a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Crear_cuenta : AppCompatActivity() {

    private lateinit var txt3: EditText
    private lateinit var txt5: EditText
    private lateinit var txt6: EditText
    private lateinit var txt20: EditText
    private lateinit var spinner_cargo: Spinner
    private lateinit var txt9: EditText
    private lateinit var txt10: EditText
    private lateinit var btn3: Button
    private lateinit var btn4: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt3 = findViewById(R.id.txt3)
        txt5 = findViewById(R.id.txt5)
        txt6 = findViewById(R.id.txt6)
        txt20 = findViewById(R.id.txt20)
        spinner_cargo = findViewById(R.id.spinner_cargo)
        txt9 = findViewById(R.id.txt9)
        txt10 = findViewById(R.id.txt10)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)

        botonregistrar()
        volver()

    }//cierra el onCreate

    fun botonregistrar() {
        btn3.setOnClickListener {
            if (txt3.text.toString().trim().isEmpty() ||
                txt5.text.toString().trim().isEmpty() ||
                txt6.text.toString().trim().isEmpty() ||
                txt20.text.toString().trim().isEmpty() ||
                spinner_cargo.selectedItem.toString().equals("Seleccione una opción", ignoreCase = false) ||
                txt9.text.toString().trim().isEmpty() ||
                txt10.text.toString().trim().isEmpty()
            ) {

                Toast.makeText(
                    this@Crear_cuenta,
                    "!Complete los campos Faltantes¡", Toast.LENGTH_SHORT
                ).show()
            } else {
                var rut = txt3.text.toString()
                var nom = txt5.text.toString()
                var ape = txt6.text.toString()
                var ema = txt20.text.toString()
                var car = spinner_cargo.selectedItem.toString()
                var con = txt9.text.toString().toInt()
                var con2 = txt10.text.toString().toInt()

                // Validamos que ambas contraseñas coincidan
                if (con != con2) {
                    Toast.makeText(this@Crear_cuenta, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                var estado = false

                var dbref = FirebaseDatabase.getInstance().getReference("Usuario")

                dbref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        for (x in snapshot.children) {
                            if (rut == x.child("rut").getValue().toString()) {
                                estado = true
                                Toast.makeText(
                                    this@Crear_cuenta,
                                    "Rut ya registrado", Toast.LENGTH_SHORT
                                ).show()
                                txt3.setText("")
                                break

                            }//cierra el if

                        }//cierra el for

                        if (estado == false) {
                            // Se le pasan los 7 argumentos requeridos por la clase Usuario (incluyendo con2)
                            var usu = Usuario(nom, ape, ema, car, con, rut, con2)
                            dbref.push().setValue(usu)
                            Toast.makeText(
                                this@Crear_cuenta,
                                "Usuario registrado", Toast.LENGTH_SHORT
                            ).show()
                            txt3.setText("")
                            txt5.setText("")
                            txt6.setText("")
                            txt20.setText("")
                            spinner_cargo.setSelection(0)
                            txt9.setText("")
                            txt10.setText("")
                        }

                    }//cierra el ondatachnage

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            this@Crear_cuenta,
                            "Error al registrar", Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        }
    }//cierra el fun botonregistrar

    fun volver() {
        btn4.setOnClickListener {
            var intent = Intent(this@Crear_cuenta, Principal::class.java)
            startActivity(intent)
        }
    }//cierra funcion volver

}//cierra la clase Crear_cuenta