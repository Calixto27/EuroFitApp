package com.example.eurofitapp.data

import com.example.eurofitapp.model.TestModel
object TestRepository{
    val testList = listOf(
        TestModel(1, "Salto Horizontal", "https://via.placeholder.com/150", "https://example.com/salto-horizontal", "Fuerza muscular"),
        TestModel(2, "Flexión de Tronco", "https://via.placeholder.com/150", "https://example.com/flexion-tronco", "Flexibilidad"),
        TestModel(3, "Test de Velocidad 10m", "https://via.placeholder.com/150", "https://example.com/velocidad-10m", "Velocidad"),
        TestModel(4, "Test de Agilidad", "https://via.placeholder.com/150", "https://example.com/agilidad", "Agilidad")
    )
}