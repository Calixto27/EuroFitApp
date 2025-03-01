package com.example.eurofitapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eurofitapp.navigation.Screens

@Composable
fun LoginScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    val storedPassword = "1234"
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ingrese su contraseña")

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Button(
            onClick = {
                if (password == storedPassword) {
                    navController.navigate(Screens.Home.route)
                } else {
                    errorMessage = "Contraseña incorrecta"
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Ingresar")
        }

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colors.error, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
