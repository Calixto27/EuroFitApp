package com.example.eurofitapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eurofitapp.ui.screens.*

sealed class Screens(val route: String) {
    object Login : Screens("login_screen")
    object Home : Screens("home_screen")
    object TestDetail : Screens("test_detail_screen/{testName}") {
        fun createRoute(testName: String) = "test_detail_screen/$testName"
    }
    object UserSettings : Screens("user_settings_screen")
    object TestResults : Screens("test_results_screen")
    object BMI : Screens("bmi_screen")
}

@Composable
fun Navigation(onThemeChange: (Boolean) -> Unit, modifier: Modifier) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route) {
        composable(Screens.Login.route) { LoginScreen(navController) }
        composable(Screens.Home.route) { HomeScreen(navController) }
        composable(Screens.TestDetail.route) { backStackEntry ->
            val testName = backStackEntry.arguments?.getString("testName") ?: ""
            TestDetailScreen(navController, testName)
        }
        composable(Screens.UserSettings.route) { UserSettingsScreen(onThemeChange) }
        composable(Screens.TestResults.route) { TestResultsScreen() }
        composable(Screens.BMI.route) { BMIScreen(navController) }
    }
}
