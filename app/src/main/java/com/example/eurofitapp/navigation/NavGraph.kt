package com.example.eurofitapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eurofitapp.ui.screens.HomeScreen
import com.example.eurofitapp.ui.screens.LoginScreen
import com.example.eurofitapp.ui.screens.TestDetailScreen
import com.example.eurofitapp.ui.screens.TestResultsScreen
import com.example.eurofitapp.ui.screens.UserSettingsScreen

sealed class Screens(val route: String) {
    object Login : Screens("login_screen")
    object Home : Screens("home_screen")
    object TestDetail : Screens("test_detail_screen/{testName}") {
        fun createRoute(testName: String) = "test_detail_screen/$testName"
    }
    object UserSettings : Screens("user_settings_screen")
    object TestResults : Screens("test_results_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route) {
        composable(Screens.Login.route) { LoginScreen(navController) }
        composable(Screens.Home.route) { HomeScreen(navController) }
        composable(Screens.TestDetail.route) { backStackEntry ->
            val testName = backStackEntry.arguments?.getString("testName") ?: ""
            TestDetailScreen(navController, testName)
        }
        composable(Screens.UserSettings.route) { UserSettingsScreen() }
        composable(Screens.TestResults.route) { TestResultsScreen() }
    }
}
