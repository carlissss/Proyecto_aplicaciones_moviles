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

class Acciones : AppCompatActivity() {

    private lateinit var tv40: TextView
    private lateinit var btn: Button
    private var usu: Usuario? = null


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_acciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv40 = findViewById(R.id.tv40)
        btn = findViewById(R.id.btn)

        usu = intent.getParcelableExtra("usu", Usuario::class.java)
        tv40.setText(usu?.nombre)



        desactivarFlecha()
        volver()

    }// cierra el onCreate


    fun volver(){
        btn.setOnClickListener {
            var intent = Intent(this@Acciones, Menu_usu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    }//cierra funcion volver

    fun desactivarFlecha(){
        onBackPressedDispatcher.addCallback(this){
            //finich()
        }

    }//cierra desactivarFlecha

}// cierra la clase Acciones