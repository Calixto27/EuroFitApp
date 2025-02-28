package com.example.eurofitapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eurofitapp.ui.screens.HomeScreen
import com.example.eurofitapp.ui.screens.LoginScreen

sealed class Screens(val route: String) {
    object Login : Screens("login_screen")
    object Home : Screens("home_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route) {
        composable(Screens.Login.route) { LoginScreen(navController) }
        composable(Screens.Home.route) { HomeScreen(navController) }
    }
}
