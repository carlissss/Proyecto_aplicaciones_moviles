package com.example.a

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Manipular_circuito : AppCompatActivity() {

    private lateinit var tv5: TextView

    private lateinit var btn10 : Button
    private lateinit var btn11 : Button
    private lateinit var btn12: Button

    private var usu: Usuario? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manipular_circuito)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv5 = findViewById(R.id.tv5)
        btn10 = findViewById(R.id.btn10)
        btn11 = findViewById(R.id.btn11)
        btn12 = findViewById(R.id.btn12)

        usu = intent.getParcelableExtra("usu", Usuario::class.java)

        tv5.setText(usu?.nombre)

        volver()
        desactivarFlecha()


    }// cierra el onCreate

    fun volver(){
        btn12.setOnClickListener {
            var intent = Intent(this@Manipular_circuito, Menu_usu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    }//cierra funcion volver
    fun desactivarFlecha(){
        onBackPressedDispatcher.addCallback(this){
            //finich()
        }

    }//cierra desactivarFlecha

}// cierra la clase Manipular_circuito