package com.example.altai_checkers.screens


import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.altai_checkers.R

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier.width(300.dp)){
                Text(stringResource(R.string.title),
                     fontSize = 48.sp,
                     lineHeight = 50.sp,
                     textAlign = TextAlign.Center)}
            ButtonWithSettings(stringResource(R.string.play_with_bot),
                               onClick = { navController.navigate("GameVsBotScreen") },
                               onSettingsClick = {/* TODO */})
            ButtonWithSettings(stringResource(R.string.play_with_friend),
                               onClick = {navController.navigate("GameVsFriendScreen")},
                               onSettingsClick = {/* TODO */})
            Button(onClick = { navController.navigate("StatisticsScreen") },
                   modifier = Modifier
                       .width(300.dp)
                       .height(80.dp),
                   content = {Text(stringResource(R.string.statistics),
                              fontSize = 24.sp)})
            Button(onClick = { navController.navigate("AboutScreen") },
                   modifier = Modifier
                       .width(300.dp)
                       .height(80.dp),
                   content = {Text(stringResource(R.string.about),
                   fontSize = 24.sp)})
        }
    }
}

@Composable
fun ButtonWithSettings(text: String, onClick: () -> Unit, onSettingsClick: () -> Unit) {
    Button(onClick = onClick,
           modifier = Modifier
               .width(300.dp)
               .height(80.dp),
           content = {Text(text, fontSize = 24.sp)
                      IconButton(onClick = onSettingsClick){
                          Icon(Icons.Filled.Settings,
                               contentDescription = stringResource(R.string.settings),
                               modifier = Modifier.fillMaxSize(0.75f))}})
}