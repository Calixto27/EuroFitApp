package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eurofitapp.data.ScoringRepository
import com.example.eurofitapp.data.TestResultRepository // 🔴 Cambio de UserPreferences a TestResultRepository
import com.example.eurofitapp.model.TestResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestDetailScreen(navController: NavController, testName: String, testResultRepository: TestResultRepository) {
    val coroutineScope = rememberCoroutineScope()
    val userData = runBlocking { testResultRepository.getAllResults().first() }

    var userValue by remember { mutableStateOf("") }
    var score by remember { mutableStateOf<Double?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(testName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Ingrese su resultado para $testName")
            OutlinedTextField(
                value = userValue,
                onValueChange = { userValue = it },
                label = { Text("Ingrese su marca") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val value = userValue.toDoubleOrNull()
                if (value != null) {
                    score = ScoringRepository.getScore(testName, userData.firstOrNull()?.id ?: 12, "Masculino", value)
                }
            }) {
                Text("Calcular Nota")
            }

            Spacer(modifier = Modifier.height(16.dp))

            score?.let {
                Text("Su nota es: $it", style = MaterialTheme.typography.h6)
                Button(onClick = {
                    coroutineScope.launch {
                        testResultRepository.insertResult(TestResult(0, testName, it))
                    }
                }) {
                    Text("Guardar Resultado")
                }
            }
        }
    }
}
