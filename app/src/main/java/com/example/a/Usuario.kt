package com.example.a
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Usuario (

    var nombre: String,
    var apellido: String,
    var email: String,
    var tipo: String,
    var contraseña: Int,
    var rut: String,
    var repetirContraseña: Int

): Parcelable