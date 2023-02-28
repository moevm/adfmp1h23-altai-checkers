package com.example.altai_checkers.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun GameVsFriendScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text("Game Versus Friend")
    }
}