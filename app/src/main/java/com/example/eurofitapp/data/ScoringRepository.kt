package com.example.eurofitapp.data

import com.example.eurofitapp.model.ScoringModel

object ScoringRepository {
    val scoringData = listOf(
        // Test Cooper - Mujeres
        ScoringModel("Test Cooper", 12, "Femenino", 1200.0, 1250.0, 2.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1250.0, 1300.0, 2.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1300.0, 1350.0, 3.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1350.0, 1400.0, 3.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1400.0, 1450.0, 4.0),
        ScoringModel("Test Cooper", 12, "Femenino", 1450.0, 1500.0, 4.5),
        ScoringModel("Test Cooper", 12, "Femenino", 1500.0, 1550.0, 5.0),

        // Test Cooper - Hombres
        ScoringModel("Test Cooper", 12, "Masculino", 1400.0, 1450.0, 2.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1450.0, 1500.0, 2.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1500.0, 1550.0, 3.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1550.0, 1600.0, 3.5),
        ScoringModel("Test Cooper", 12, "Masculino", 1600.0, 1650.0, 4.0),
        ScoringModel("Test Cooper", 12, "Masculino", 1650.0, 1700.0, 4.5),

        // Velocidad 5x10 - Mujeres
        ScoringModel("Velocidad 5x10", 12, "Femenino", 15.8, 16.2, 2.0),
        ScoringModel("Velocidad 5x10", 12, "Femenino", 15.4, 15.8, 2.5),
        ScoringModel("Velocidad 5x10", 12, "Femenino", 15.0, 15.4, 3.0),
        ScoringModel("Velocidad 5x10", 12, "Femenino", 14.6, 15.0, 3.5),
        ScoringModel("Velocidad 5x10", 12, "Femenino", 14.2, 14.6, 4.0),

        // Velocidad 5x10 - Hombres
        ScoringModel("Velocidad 5x10", 12, "Masculino", 15.6, 16.0, 2.0),
        ScoringModel("Velocidad 5x10", 12, "Masculino", 15.2, 15.6, 2.5),
        ScoringModel("Velocidad 5x10", 12, "Masculino", 14.8, 15.2, 3.0),
        ScoringModel("Velocidad 5x10", 12, "Masculino", 14.4, 14.8, 3.5),
        ScoringModel("Velocidad 5x10", 12, "Masculino", 14.0, 14.4, 4.0),

        // Lanzamiento de balón - Mujeres
        ScoringModel("Lanzamiento de balón", 12, "Femenino", 3.80, 4.10, 2.0),
        ScoringModel("Lanzamiento de balón", 12, "Femenino", 4.10, 4.40, 2.5),
        ScoringModel("Lanzamiento de balón", 12, "Femenino", 4.40, 4.70, 3.0),

        // Lanzamiento de balón - Hombres
        ScoringModel("Lanzamiento de balón", 12, "Masculino", 4.70, 5.00, 2.0),
        ScoringModel("Lanzamiento de balón", 12, "Masculino", 5.00, 5.30, 2.5),
        ScoringModel("Lanzamiento de balón", 12, "Masculino", 5.30, 5.60, 3.0),
    )

    fun getScore(testName: String, age: Int, gender: String, userValue: Double): Double? {
        return scoringData.find {
            it.testName == testName &&
                    it.age == age &&
                    it.gender == gender &&
                    userValue in it.minValue..it.maxValue
        }?.score
    }
}
