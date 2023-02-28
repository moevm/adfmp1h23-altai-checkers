package com.example.altai_checkers.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.altai_checkers.R

@Composable
fun AboutScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(stringResource(R.string.about),
                 fontSize = 50.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(stringResource(R.string.about_info),
                 fontSize = 24.sp,
                 textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Text(stringResource(R.string.developers),
                fontSize = 24.sp,
                textAlign = TextAlign.Center)
        }
    }
}