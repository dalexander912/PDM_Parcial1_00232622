package com.example.parcial1pdm.models

data class Producto (
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String,
    val tipo: TipoProducto
)

enum class TipoProducto {
    PUPUSA,
    BEBIDA
}

data class ItemOrden(
    val producto: Producto,
    val cantidad: Int
)