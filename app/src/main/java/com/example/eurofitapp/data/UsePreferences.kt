package com.example.eurofitapp.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// DataStore Singleton
private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val AGE_KEY = intPreferencesKey("age")
        private val HEIGHT_KEY = doublePreferencesKey("height")
        private val WEIGHT_KEY = doublePreferencesKey("weight")
        private val GENDER_KEY = stringPreferencesKey("gender")
        private val RESULTS_KEY = stringPreferencesKey("test_results")
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }

    // Obtener valores almacenados
    val userData: Flow<UserData> = context.dataStore.data.map { preferences ->
        UserData(
            age = preferences[AGE_KEY] ?: 15,
            height = preferences[HEIGHT_KEY] ?: 170.0,
            weight = preferences[WEIGHT_KEY] ?: 70.0,
            gender = preferences[GENDER_KEY] ?: "Masculino"
        )
    }

    // Guardar valores en DataStore
    suspend fun saveUserData(age: Int, height: Double, weight: Double, gender: String) {
        context.dataStore.edit { preferences ->
            preferences[AGE_KEY] = age
            preferences[HEIGHT_KEY] = height
            preferences[WEIGHT_KEY] = weight
            preferences[GENDER_KEY] = gender
        }
    }

    // Guardar resultados de pruebas
    suspend fun saveTestResult(testName: String, score: Double) {
        context.dataStore.edit { preferences ->
            val currentResults = preferences[RESULTS_KEY] ?: ""
            val updatedResults = "$currentResults\n$testName: $score"
            preferences[RESULTS_KEY] = updatedResults
        }
    }

    // Obtener resultados de pruebas almacenados
    val testResults: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[RESULTS_KEY] ?: ""
    }

    // Obtener estado del modo oscuro
    val darkMode: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[DARK_MODE_KEY] ?: false
    }

    // Guardar estado del modo oscuro
    suspend fun saveDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkMode
        }
    }
}

// Modelo de datos del usuario
data class UserData(val age: Int, val height: Double, val weight: Double, val gender: String)
