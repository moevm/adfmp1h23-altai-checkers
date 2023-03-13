package com.example.altai_checkers.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.altai_checkers.R

@Composable
fun MainScreen(navController: NavHostController) {
    var showGameSettingsDialog by remember {mutableStateOf(false)}
    var gamemode by remember {mutableStateOf("")}
    var onConfirm by remember {mutableStateOf({})}
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                stringResource(R.string.title),
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Button(
                    onClick = {
                        showGameSettingsDialog = true
                        gamemode = "bot"
                        onConfirm = {
                            showGameSettingsDialog = false
                            navController.navigate("GameVsBotScreen")
                        }},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    content = {
                        Text(
                            stringResource(R.string.play_with_bot),
                            fontSize = 24.sp
                        )
                    }
                )
                Button(
                    onClick = {
                        showGameSettingsDialog = true
                        gamemode = "friend"
                        onConfirm = {
                            showGameSettingsDialog = false
                            navController.navigate("GameVsFriendScreen")
                        }},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    content = {
                        Text(
                            stringResource(R.string.play_with_friend),
                            fontSize = 24.sp
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        onClick = { navController.navigate("AboutScreen") },
                        modifier = Modifier.size(80.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate("StatisticsScreen") },
                        modifier = Modifier.size(80.dp)
                    ) {
                        Icon(
                            ImageVector.vectorResource(R.drawable.outline_leaderboard_24),
                            contentDescription = stringResource(R.string.statistics),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
        if (showGameSettingsDialog) {
            GameSettingsDialog(gamemode,
                onDismiss = {showGameSettingsDialog = false},
                onConfirm = onConfirm)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GameSettingsDialog(
    gamemode: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var firstPlayer = "First Player"
    var secondPlayer = "Second Player"
    var state by remember {mutableStateOf(false)}
    var time = 20
    var addition = 3
    Dialog(onDismissRequest = { onDismiss() },
           properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Column(modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(stringResource(R.string.player_1_name),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center)
                        TextField(
                            value = firstPlayer, //TODO
                            placeholder = { Text("Игрок 1")},
                            onValueChange = { firstPlayer = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (gamemode == "friend")
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(stringResource(R.string.player_2_name),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center)
                            TextField(
                                value = secondPlayer, //TODO
                                placeholder = { Text("Игрок 2")},
                                onValueChange = { secondPlayer = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                }

                Column(Modifier.selectableGroup()) {
                    Row(){
                        RadioButton(selected = state,
                                    onClick = { state = true })
                        Text(stringResource(R.string.with_addition),
                             fontSize = 22.sp) }
                    Row() {
                        RadioButton(selected = !state,
                                    onClick = { state = false })
                        Text(stringResource(R.string.without_addition),
                             fontSize = 22.sp) } }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(stringResource(R.string.time),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center)
                        TextField(
                            value = time.toString(), //TODO
                            placeholder = { Text("Введите время")},
                            onValueChange = { },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (state)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(stringResource(R.string.addition_time),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center)
                            TextField(
                                value = addition.toString(), //TODO
                                placeholder = { Text("Введите добавочное время")},
                                onValueChange = { },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                }

                Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = stringResource(R.string.statistics),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = onConfirm,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            Icons.Outlined.PlayArrow,
                            contentDescription = stringResource(R.string.statistics),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}