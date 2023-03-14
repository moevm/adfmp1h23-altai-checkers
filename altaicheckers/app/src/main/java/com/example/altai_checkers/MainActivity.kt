package com.example.altai_checkers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.altai_checkers.screens.*
import com.example.altai_checkers.ui.theme.AltaicheckersTheme
import com.example.altai_checkers.viewmodels.MainScreenViewModel
import com.example.altai_checkers.viewmodels.StatisticsScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltaicheckersTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val mainScreenViewModel = MainScreenViewModel()
                    val statisticsScreenViewModel = StatisticsScreenViewModel()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable("MainScreen") {
                            MainScreen(
                                navController,
                                mainScreenViewModel
                            )
                        }
                        composable("GameVsBotScreen") { GameVsBotScreen(navController) }
                        composable("GameVsFriendScreen") { GameVsFriendScreen(navController) }
                        composable("StatisticsScreen") {
                            StatisticsScreen(
                                navController,
                                statisticsScreenViewModel
                            )
                        }
                        composable("AboutScreen") { AboutScreen(navController) }
                    }
                }
            }
        }
    }
}