package com.example.a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ver_perfil : AppCompatActivity() {

    private lateinit var tv: TextView
    private lateinit var tv15: TextView
    private lateinit var tv27: TextView
    private lateinit var tv30: TextView
    private lateinit var tv33: TextView
    private lateinit var tv36: TextView
    private lateinit var tv39: TextView
    private lateinit var btn14: Button

    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv = findViewById(R.id.tv)
        tv15 = findViewById(R.id.tv15)
        tv27 = findViewById(R.id.tv27)
        tv30 = findViewById(R.id.tv30)
        tv33 = findViewById(R.id.tv33)
        tv36 = findViewById(R.id.tv36)
        tv39 = findViewById(R.id.tv39)
        btn14 = findViewById(R.id.btn14)

        usu = intent.getParcelableExtra("usu", Usuario::class.java)
        tv.setText(usu?.nombre)

        tv15.setText(usu?.rut?.lowercase())
        tv27.setText(usu?.nombre?.lowercase())
        tv30.setText(usu?.apellido?.lowercase())
        tv33.setText(usu?.email?.lowercase())
        tv36.setText(usu?.tipo?.lowercase())
        tv39.setText(usu?.contraseña?.toString())


        desactivarFlecha()
        volver()
    }//cierra onCreate

    fun volver(){
        btn14.setOnClickListener {
            var intent = Intent(this@Ver_perfil, Menu_usu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    }//cierra funcion volver
    fun desactivarFlecha(){
        onBackPressedDispatcher.addCallback(this){
            //finich()
        }

    }//cierra desactivarFlecha

}//cierra la clase Ver_perfil