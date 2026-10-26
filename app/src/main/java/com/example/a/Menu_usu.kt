package com.example.a

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu_usu : AppCompatActivity() {

    private lateinit var tv4: TextView
    private lateinit var btn5 : Button
    private lateinit var btn6 : Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button

    private var usu: Usuario? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_usu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv4 = findViewById(R.id.tv4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        usu = intent.getParcelableExtra("usu", Usuario::class.java)

        tv4.setText(usu?.nombre)

        Log.i("Nombre", "${usu?.nombre}")
        Log.i("Email", "${usu?.email}")
        Log.i("Tipo", "${usu?.tipo}")
        Log.i("Contraseña", "${usu?.contraseña}")
        Log.i("Rut", "${usu?.rut}")

        btn5()
        btn6()
        btn7()
        btn8()
        btn9()
        desactivarFlecha()

    }// cierra el onCreate

    fun btn5(){
        btn5.setOnClickListener {
            var intent = Intent(this@Menu_usu, Manipular_circuito::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }

    }//cierra funcion btn5

    fun btn6(){
        btn6.setOnClickListener {
            var intent = Intent(this@Menu_usu, Acciones::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }


    }//cierra funcion btn6

    fun btn7(){
        btn7.setOnClickListener {
            var intent = Intent(this@Menu_usu, Estadisticas::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }

    }//cierra funcion btn8

    fun btn8(){
        btn8.setOnClickListener {
            var intent = Intent(this@Menu_usu, Ver_perfil::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }

    }//cierra funcion btn8

    fun btn9(){
        btn9.setOnClickListener {
            var a = AlertDialog.Builder(this@Menu_usu)
            a.setTitle("Pregunta")
            a.setMessage("¿Desea cerrar sesión?")
            a.setIcon(R.drawable.pregunta)
            a.setCancelable(false)

            a.setNegativeButton("Cancelar") { dialog, wich ->
                dialog.dismiss()
            }

            a.setPositiveButton("Aceptar") { dialog, wich ->
                var intent = Intent(this@Menu_usu, Principal::class.java)
                startActivity(intent)
            }
            a.show()
        }


    }//cierra funcion btn9

    fun desactivarFlecha(){
        onBackPressedDispatcher.addCallback(this){
            //finich()
        }

    }//cierra desactivarFlecha
}// cierra la clase menu