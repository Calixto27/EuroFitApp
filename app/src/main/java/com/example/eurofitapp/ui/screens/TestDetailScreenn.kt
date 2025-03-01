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
import com.example.eurofitapp.data.ScoringRepository
import com.example.eurofitapp.data.UserPreferences
import com.example.eurofitapp.model.UserData
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestDetailScreen(navController: NavController, testName: String) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    // Obtener datos del usuario de manera segura
    val userData = runBlocking { userPreferences.userData.first() }

    var userValue by remember { mutableStateOf("") }
    var score by remember { mutableStateOf<Double?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(testName) }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Ingrese su resultado para $testName")

            OutlinedTextField(
                value = userValue,
                onValueChange = { userValue = it },
                label = { Text("Ingrese su marca") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val value = userValue.toDoubleOrNull()
                if (value != null) {
                    score = ScoringRepository.getScore(testName, userData.age, userData.gender, value)
                }
            }) {
                Text("Calcular Nota")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (score != null) {
                Text(text = "Su nota es: $score", style = MaterialTheme.typography.h6)

                Button(onClick = {
                    coroutineScope.launch {
                        userPreferences.saveTestResult(testName, score!!)
                    }
                }) {
                    Text("Guardar Resultado")
                }
            } else {
                Text(text = "No se encontró una nota para su marca", color = MaterialTheme.colors.error)
            }
        }
    }
}
