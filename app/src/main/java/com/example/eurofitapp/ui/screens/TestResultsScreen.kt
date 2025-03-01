package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eurofitapp.data.UserPreferences

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestResultsScreen() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val results by userPreferences.testResults.collectAsState(initial = "")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Resultados Guardados") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Historial de pruebas:")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = results.ifEmpty { "No hay resultados guardados" })
        }
    }
}
