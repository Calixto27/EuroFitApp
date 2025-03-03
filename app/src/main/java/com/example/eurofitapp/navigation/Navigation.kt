package com.example.eurofitapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eurofitapp.ui.screens.*
import com.example.eurofitapp.data.UserRepository
import com.example.eurofitapp.data.TestResultRepository

@Composable
fun Navigation(
    userRepository: UserRepository,
    testResultRepository: TestResultRepository,
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
        modifier = modifier
    ) {
        composable(Screens.Login.route) {
            LoginScreen(navController, userRepository)
        }
        composable(Screens.Home.route) { HomeScreen(navController) }
        composable(Screens.TestDetail.route) { backStackEntry ->
            val testName = backStackEntry.arguments?.getString("testName") ?: ""
            TestDetailScreen(navController, testName, testResultRepository)
        }
        composable(Screens.UserSettings.route) {
            UserSettingsScreen(
                userRepository = userRepository,
                navController = navController,
                onThemeChange = onThemeChange
            )
        }
        composable(Screens.TestResults.route) {
            TestResultsScreen(navController, testResultRepository)
        }
        composable(Screens.BMI.route) {
            BMIScreen(navController, userRepository)
        }
    }
}
