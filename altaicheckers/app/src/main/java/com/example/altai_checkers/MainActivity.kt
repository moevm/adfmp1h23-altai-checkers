package com.example.altai_checkers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.altai_checkers.screens.*
import com.example.altai_checkers.ui.theme.AltaicheckersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltaicheckersTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable("MainScreen") { MainScreen(navController) }
                        composable("GameVsBotScreen") { GameVsBotScreen(navController) }
                        composable("GameVsFriendScreen") { GameVsFriendScreen(navController) }
                        composable("StatisticsScreen") { StatisticsScreen(navController) }
                        composable("AboutScreen") { AboutScreen(navController) }
                    }
                }
            }
        }
    }
}