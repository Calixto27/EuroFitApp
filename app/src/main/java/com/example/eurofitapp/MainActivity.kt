package com.example.eurofitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.eurofitapp.data.AppDatabase
import com.example.eurofitapp.data.UserRepository
import com.example.eurofitapp.data.TestResultRepository
import com.example.eurofitapp.navigation.Navigation
import com.example.eurofitapp.ui.theme.EuroFitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)

        val userRepository = UserRepository(database.userDao())
        val testResultRepository = TestResultRepository(database.testResultDao())

        setContent {
            EuroFitAppTheme {
                Navigation(userRepository, testResultRepository, onThemeChange = {})
            }
        }
    }
}
