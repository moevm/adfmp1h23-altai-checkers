package com.example.altai_checkers


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MainPage() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.SpaceEvenly) {
            Text(stringResource(R.string.title),
                 fontSize = 24.sp)
            ButtonWithSettings(stringResource(R.string.play_with_bot),
                               onClick = {/* TODO */},
                               onSettingsClick = {/* TODO */})
            ButtonWithSettings(stringResource(R.string.play_with_friend),
                               onClick = {/* TODO */},
                               onSettingsClick = {/* TODO */})
            Button(onClick = { /* TODO */ },
                   modifier = Modifier.width(250.dp)
                                      .height(80.dp),
                   content = {Text(stringResource(R.string.statistics),
                              fontSize = 18.sp)})
        }
    }
}

@Composable
fun ButtonWithSettings(text: String, onClick: () -> Unit, onSettingsClick: () -> Unit) {
    Button(onClick = onClick,
           modifier = Modifier.width(250.dp)
                              .height(80.dp),
           content = {Text(text, fontSize = 18.sp)
                      IconButton(onClick = onSettingsClick){
                          Icon(Icons.Filled.Settings,
                               contentDescription = stringResource(R.string.settings),
                               modifier = Modifier.fillMaxSize(0.65f))}})
}