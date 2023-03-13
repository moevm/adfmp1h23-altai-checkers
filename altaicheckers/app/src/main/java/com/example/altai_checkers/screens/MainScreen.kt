package com.example.altai_checkers.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import com.example.altai_checkers.viewmodels.MainScreenViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainScreenViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var showGameSettingsDialog by remember {mutableStateOf(false)}
    var showErrorDialog by remember {mutableStateOf(false)}
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
                            viewModel.checkBeforeStart(gamemode,
                                {
                                    showGameSettingsDialog = false
                                    navController.navigate("GameVsBotScreen")
                                },
                                { showErrorDialog = true })
                        }
                    },
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
                            viewModel.checkBeforeStart(gamemode,
                                {
                                    showGameSettingsDialog = false
                                    navController.navigate("GameVsFriendScreen")
                                },
                                { showErrorDialog = true })
                        }
                    },
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
        if (showGameSettingsDialog)
            Dialog(
                onDismissRequest = { showGameSettingsDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(25.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    stringResource(R.string.first_player_name),
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Center
                                )
                                TextField(
                                    value = uiState.firstPlayer,
                                    placeholder = { Text("Игрок 1") },
                                    onValueChange = { viewModel.updatePlayerName(it, 1) },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            if (gamemode == "friend")
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Text(
                                        stringResource(R.string.second_player_name),
                                        fontSize = 22.sp,
                                        textAlign = TextAlign.Center
                                    )
                                    TextField(
                                        value = uiState.secondPlayer,
                                        placeholder = { Text("Игрок 2") },
                                        onValueChange = { viewModel.updatePlayerName(it, 2) },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                        }

                        Column(Modifier.selectableGroup()) {
                            Row {
                                RadioButton(selected = uiState.withAddition,
                                    onClick = { viewModel.setWithAddition(true) })
                                Text(
                                    stringResource(R.string.with_addition),
                                    fontSize = 22.sp
                                )
                            }
                            Row {
                                RadioButton(selected = !uiState.withAddition,
                                    onClick = { viewModel.setWithAddition(false) })
                                Text(
                                    stringResource(R.string.without_addition),
                                    fontSize = 22.sp
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    stringResource(R.string.time),
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Center
                                )
                                TextField(
                                    value = uiState.time.toString(),
                                    placeholder = { Text("Введите время") },
                                    onValueChange = { viewModel.updateTime(it.toIntOrNull()) },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            if (uiState.withAddition)
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Text(
                                        stringResource(R.string.addition_time),
                                        fontSize = 22.sp,
                                        textAlign = TextAlign.Center
                                    )
                                    TextField(
                                        value = uiState.additionTime.toString(),
                                        placeholder = { Text("Введите добавочное время") },
                                        onValueChange = { viewModel.updateAdditionTime(it.toIntOrNull()) },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            IconButton(
                                onClick = { showGameSettingsDialog = false },
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
        if (showErrorDialog)
            AlertDialog(
                onDismissRequest = {
                    showErrorDialog = false
                    viewModel.setError("")
                },
                title = {
                    Text(text = "Неверные данные")
                },
                text = {
                    Text(uiState.error)
                },
                confirmButton = {
                    Button(

                        onClick = {
                            showErrorDialog = false
                            viewModel.setError("")
                        }) {
                        Text("Ок")
                    }
                }
            )
    }
}