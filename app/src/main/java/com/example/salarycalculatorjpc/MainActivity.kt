package com.example.salarycalculatorjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.salarycalculatorjpc.ui.theme.SalaryCalculatorJPCTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalaryCalculatorJPCTheme {
                // колір з теми
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "screen_1"
                    ) {
                        // Екран 1
                        composable("screen_1") {
                            Screen1 {
                                Bundle().apply {
                                    putFloat("salary", it)}
                                navController.navigate("screen_2/$it")
                            }
                        }
                        // Екран 2
                            composable(
                            "screen_2/{salary}",
                            arguments = listOf(navArgument("salary") { type = NavType.FloatType })
                        ) { backStackEntry ->
                            val salary = backStackEntry.arguments?.getFloat("salary") ?: 0.0f
                            Screen2(salary = salary.toDouble()) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}