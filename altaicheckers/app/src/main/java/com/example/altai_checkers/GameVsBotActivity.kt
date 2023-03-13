package com.example.altai_checkers

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.altai_checkers.items.Field
import com.example.altai_checkers.items.Game
import com.example.altai_checkers.screens.GameVsBotScreen
import com.example.altai_checkers.ui.theme.AltaicheckersTheme

class GameVsBotActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltaicheckersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    val context = LocalContext.current
    val intent = (context as GameVsBotActivity).intent
//    val gameType = intent.getStringExtra("gameType")
    val player1 = intent.getStringExtra("player1")
    val player2 = intent.getStringExtra("player2")
    val game = Game("VSBot", Field(), player1, player2)
    /*val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "GameVsBotScreen") {
        composable("GameVsBotScreen") { GameVsBotScreen(navController, game) }
        composable("GameVsFriendScreen") { GameVsFriendScreen(navController) }
    }*/
    GameVsBotScreen(game)
}