package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.eurofitapp.data.UserPreferences
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestResultsScreen() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val results by userPreferences.testResults.collectAsState(initial = "")
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Resultados Guardados") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Historial de pruebas:", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = results.ifEmpty { "No hay resultados guardados" })

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                coroutineScope.launch {
                    shareResults(context, results)
                }
            }) {
                Text("Exportar Resultados")
            }
        }
    }
}

fun shareResults(context: android.content.Context, results: String) {
    if (results.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Resultados de Pruebas Físicas")
            putExtra(Intent.EXTRA_TEXT, results)
        }
        context.startActivity(Intent.createChooser(intent, "Compartir Resultados"))
    }
}
