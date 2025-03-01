package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.eurofitapp.data.UserPreferences
import com.example.eurofitapp.model.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserSettingsScreen() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    val userData = runBlocking { userPreferences.userData.first() }

    var age by remember { mutableStateOf(userData.age.toString()) }
    var height by remember { mutableStateOf(userData.height.toString()) }
    var weight by remember { mutableStateOf(userData.weight.toString()) }
    var gender by remember { mutableStateOf(userData.gender) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Configuración de Usuario") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Edad")
            OutlinedTextField(value = age, onValueChange = { age = it })

            Text("Altura (cm)")
            OutlinedTextField(value = height, onValueChange = { height = it })

            Text("Peso (kg)")
            OutlinedTextField(value = weight, onValueChange = { weight = it })

            Text("Género")
            OutlinedTextField(value = gender, onValueChange = { gender = it })

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                coroutineScope.launch {
                    userPreferences.saveUserData(
                        age.toIntOrNull() ?: 15,
                        height.toDoubleOrNull() ?: 170.0,
                        weight.toDoubleOrNull() ?: 70.0,
                        gender
                    )
                }
            }) {
                Text("Guardar")
            }
        }
    }
}
