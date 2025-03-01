package com.example.eurofitapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.eurofitapp.data.TestRepository
import com.example.eurofitapp.model.TestModel
import com.example.eurofitapp.navigation.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pruebas Físicas") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.UserSettings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                    IconButton(onClick = { navController.navigate(Screens.TestResults.route) }) {
                        Icon(Icons.Default.List, contentDescription = "Resultados")
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(TestRepository.testList) { test ->
                TestItem(test, navController)
            }
        }
    }
}


@Composable
fun TestItem(test: TestModel, navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { uriHandler.openUri(test.descriptionUrl) },
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = test.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = test.name, style = MaterialTheme.typography.h6)
        }
    }
}
