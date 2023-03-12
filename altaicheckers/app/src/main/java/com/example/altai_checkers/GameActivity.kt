package com.example.altai_checkers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.altai_checkers.items.Field
import com.example.altai_checkers.items.Game
import com.example.altai_checkers.screens.GameVsBotScreen
import com.example.altai_checkers.screens.GameVsFriendScreen
import com.example.altai_checkers.ui.theme.AltaicheckersTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltaicheckersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val intent = (context as GameActivity).intent
                    val gameType = intent.getStringExtra("gameType")
                    val player1 = intent.getStringExtra("player1")
                    val player2 = intent.getStringExtra("player2")
                    val game = Game(gameType, Field(), player1, player2)
                    when(gameType) {
                        "VSBot" -> GameVsBotScreen(game)
                        "VSFriend" -> GameVsFriendScreen(game)
                    }
                }
            }
        }
    }
}