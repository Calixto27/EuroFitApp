package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eurofitapp.data.UserPreferences
import com.example.eurofitapp.model.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BMIScreen(navController: NavController) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    // Obtener los datos del usuario de manera segura
    val userData = runBlocking { userPreferences.userData.first() }
    val bmi = calculateBMI(userData.weight, userData.height)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Índice de Masa Corporal (IMC)") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Peso: ${userData.weight} kg", style = MaterialTheme.typography.h6)
            Text(text = "Altura: ${userData.height} cm", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "IMC: %.2f".format(bmi), style = MaterialTheme.typography.h4)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = getBMICategory(bmi),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

fun calculateBMI(weight: Double, heightCm: Double): Double {
    val heightM = heightCm / 100
    return weight / (heightM * heightM)
}

fun getBMICategory(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Bajo peso"
        bmi in 18.5..24.9 -> "Peso normal"
        bmi in 25.0..29.9 -> "Sobrepeso"
        else -> "Obesidad"
    }
}
