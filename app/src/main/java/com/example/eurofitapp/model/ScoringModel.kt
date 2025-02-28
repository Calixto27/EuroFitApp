package com.example.eurofitapp.model

data class ScoringModel(
    val testName: String,
    val age: Int,
    val gender: String, // "Masculino" o "Femenino"
    val minValue: Double, // Mínima marca aceptada
    val maxValue: Double, // Máxima marca aceptada
    val score: Double // Nota correspondiente
)
