package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eurofitapp.data.UserRepository
import com.example.eurofitapp.navigation.Screens
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController, userRepository: UserRepository) {
    val coroutineScope = rememberCoroutineScope()
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    // Obtener la contraseña almacenada de SQLite
    val storedPassword = runBlocking {
        userRepository.getUserData().first()?.password ?: "1234" // ← 🔴 AQUÍ ESTABA EL ERROR
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Inicio de Sesión") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Ingrese su contraseña")

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") }
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

            Button(
                onClick = { showChangePasswordDialog = true },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Cambiar Contraseña")
            }

            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = MaterialTheme.colors.error)
            }
        }
    }

    if (showChangePasswordDialog) {
        ChangePasswordDialog(
            onDismiss = { showChangePasswordDialog = false },
            onChangePassword = { oldPassword, newPassword ->
                if (oldPassword == storedPassword) {
                    coroutineScope.launch {
                        userRepository.updatePassword(newPassword)
                        showChangePasswordDialog = false
                    }
                } else {
                    errorMessage = "Contraseña actual incorrecta"
                }
            }
        )
    }
}

@Composable
fun ChangePasswordDialog(onDismiss: () -> Unit, onChangePassword: (String, String) -> Unit) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cambiar Contraseña") },
        text = {
            Column {
                OutlinedTextField(
                    value = oldPassword,
                    onValueChange = { oldPassword = it },
                    label = { Text("Contraseña Actual") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Nueva Contraseña") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onChangePassword(oldPassword, newPassword) }) {
                Text("Cambiar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
