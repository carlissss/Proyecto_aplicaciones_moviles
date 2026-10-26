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
import org.w3c.dom.Text

class Estadisticas : AppCompatActivity() {

    private lateinit var tv7: TextView
    private lateinit var tv11: TextView
    private lateinit var tv18: TextView
    private lateinit var tv21: TextView
    private lateinit var tv24: TextView
    private lateinit var btn13: Button

    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estadisticas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv7 = findViewById(R.id.tv7)
        tv11 = findViewById(R.id.tv11)
        tv18 = findViewById(R.id.tv18)
        tv21 = findViewById(R.id.tv21)
        tv24 = findViewById(R.id.tv24)
        btn13 = findViewById(R.id.btn13)

        usu = intent.getParcelableExtra("usu", Usuario::class.java)
        tv7.setText(usu?.nombre)





        volver()
        desactivarFlecha()

    }// cierra onCreate

    fun volver(){
        btn13.setOnClickListener {
            var intent = Intent(this@Estadisticas, Menu_usu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    }//cierra funcion volver


    fun desactivarFlecha(){
        onBackPressedDispatcher.addCallback(this){
            //finich()
        }

    }//cierra desactivarFlecha

}// cierra la clase Estadisticas