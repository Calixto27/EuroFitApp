package com.example.eurofitapp.navigation

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
