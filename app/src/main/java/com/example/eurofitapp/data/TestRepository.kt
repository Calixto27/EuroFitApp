package com.example.eurofitapp.data

import com.example.eurofitapp.model.TestModel

object TestRepository {
    val testList = listOf(
        TestModel(1, "Test Cooper", "https://example.com/images/cooper.jpg", "https://example.com/cooper", "Resistencia"),
        TestModel(2, "Velocidad 5x10", "https://example.com/images/velocidad.jpg", "https://example.com/velocidad", "Velocidad"),
        TestModel(3, "Lanzamiento de balón", "https://example.com/images/lanzamiento.jpg", "https://example.com/lanzamiento", "Fuerza"),
        TestModel(4, "Salto horizontal", "https://example.com/images/salto.jpg", "https://example.com/salto", "Potencia"),
        TestModel(5, "Flexión de brazos", "https://example.com/images/flexion.jpg", "https://example.com/flexion", "Fuerza"),
        TestModel(6, "Abdominales 30", "https://example.com/images/abdominales.jpg", "https://example.com/abdominales", "Resistencia muscular")
    )
}
