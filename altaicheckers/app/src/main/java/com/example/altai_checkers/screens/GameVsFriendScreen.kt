package com.example.altai_checkers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.altai_checkers.R
import com.example.altai_checkers.items.Cell
import com.example.altai_checkers.items.EndGameDialog
import com.example.altai_checkers.items.Field
import com.example.altai_checkers.viewmodels.Game
import com.example.altai_checkers.items.GameTimer

@Composable
fun GameVsFriendScreen(navController: NavHostController, game: Game = viewModel()) {
    var pauseState by remember { mutableStateOf(false) }
    var drawState1 by remember { mutableStateOf(false) }
    var drawState2 by remember { mutableStateOf(false) }
    var defeatState1 by remember { mutableStateOf(false) }
    var defeatState2 by remember { mutableStateOf(false) }
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    game.initSettings("friend")
    if (game.getField().showDialog.value)
        game.getField().setPossibleMovies(
            game.getField()
                .getCells()[game.getField().selectCoord.value].getPossibleMoveFields(game.getField())
        )
    else {
        game.getField().unsetPossibleMovies()
    }
    val uiState by game.uiState.collectAsState()
    if (uiState.isGameEnd) {
        EndGameDialog(
            onDismiss = { /*TODO*/ },
            onConfirm = {
                navController.popBackStack()
            }
        )
    }
    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
    LaunchedEffect(game.isWhiteMove.value) {
        val message: String = if (game.isWhiteMove.value) "Ход игрока: ${uiState.player1}" else "Ход игрока: ${uiState.player2}"
        snackbarHostState.value.showSnackbar(message = message, duration = SnackbarDuration.Short)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .rotate(180f)
                .height(height / 23)
        ) {
            DrawButtonVSFriend2(onSettingsClick = {
                drawState2 = true
            })
            DefeatButtonVSFriend2(onSettingsClick = {
                defeatState2 = true
            })
            ReverseButtonVSFriend2(onSettingsClick = {

            })
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(height / 23)
                .rotate(180f)
        ) {
            Text(
                text = "Чёрные",
                fontSize = 22.sp,
            )
            Text(
                text = "0.5",
                fontSize = 18.sp,
            )
            GameTimer(uiState.time2)
        }

        LazyColumn(
            modifier = Modifier
                .padding(start = width / 20, end = width / 20)
        ) {
            items(game.getField().getCells().size / 7) {
                val index = it
                Row(
                    modifier = Modifier
                ) {
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7])}
                    )
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7 + 1],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7 + 1])}
                    )
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7 + 2],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7 + 2])}
                    )
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7 + 3],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7 + 3])}
                    )
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7 + 4],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7 + 4])}
                    )
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7 + 5],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7 + 5])}
                    )
                    FieldCellVSFriend(
                        Modifier.weight(2f),
                        FontWeight.Normal,
                        game.getField().getCells()[index * 7 + 6],
                        game.getField(),
                        height,
                        onClick = {game.playerTurn(game.getField().getCells()[index * 7 + 6])}
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(height / 23)
        ) {
            Text(
                text = "Белые",
                fontSize = 22.sp,
            )
            Text(
                text = "0.5",
                fontSize = 18.sp,
            )
            GameTimer(uiState.time1)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(height / 23)
        ) {
            DrawButtonVSFriend1(onSettingsClick = {
                drawState1 = true
            })
            DefeatButtonVSFriend1(onSettingsClick = {
                defeatState1 = true
            })
            ReverseButtonVSFriend1(onSettingsClick = {

            })
            PauseButtonVSFriend(onSettingsClick = {
                pauseState = true
            })
        }
    }
    if (pauseState) {
        PauseDialogVSFriend(onDismiss = { },
            onConfirm = { pauseState = false })
    }
    if (drawState1) {
        DrawDialogVSFriend1(onRefuse = { drawState1 = false },
            onConfirm = {
                drawState1 = false
                navController.popBackStack()
            })
    }
    if (drawState2) {
        DrawDialogVSFriend2(onRefuse = { drawState2 = false },
            onConfirm = {
                drawState2 = false
                navController.popBackStack()
            })
    }
    if (defeatState1) {
        DefeatDialogVSFriend1(onDismiss = { },
            onConfirm = {
                defeatState1 = false
                navController.navigate("MainScreen")
            })
    }
    if (defeatState2) {
        DefeatDialogVSFriend2(onDismiss = { },
            onConfirm = {
                defeatState2 = false
                navController.navigate("MainScreen")
            })
    }
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = if (game.isWhiteMove.value) Alignment.TopCenter else Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState.value,
            snackbar = {data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier.rotate(if (game.isWhiteMove.value) 0f else 180f).fillMaxWidth(),
                )
            }
        )

    }

}

@Composable
fun FieldCellVSFriend(
    modifier: Modifier,
    fontWeight: FontWeight,
    cell: Cell,
    field: Field,
    height: Dp,
    onClick: () -> Unit
) {
    if (cell.figure.figureId == 0) {
        Text(
            text = "",
            modifier = modifier
                .height(height / (35 / 2))
                .background(cell.fill),
            fontWeight = fontWeight
        )
    } else {
        val figures = mapOf(
            6 to ImageVector.vectorResource(R.drawable.white_pawn),
            7 to ImageVector.vectorResource(R.drawable.white_bishop),
            8 to ImageVector.vectorResource(R.drawable.white_rook),
            9 to ImageVector.vectorResource(R.drawable.white_queen),
            10 to ImageVector.vectorResource(R.drawable.white_king),
            1 to ImageVector.vectorResource(R.drawable.black_pawn),
            2 to ImageVector.vectorResource(R.drawable.black_bishop),
            3 to ImageVector.vectorResource(R.drawable.black_rook),
            4 to ImageVector.vectorResource(R.drawable.black_queen),
            5 to ImageVector.vectorResource(R.drawable.black_king),
            11 to ImageVector.vectorResource(R.drawable.possible_move)
        )
        Image(
            imageVector = figures[cell.figure.figureId]!!, contentDescription = "Фигура",
            modifier = modifier
                .height(height / (35 / 2))
                .background(cell.fill)
                .clickable(onClick = {
//                    if (field.selectCoord.value == 0) {
//                        field.selectCoord.value = cell.coord
//                        field.showDialog.value = !field.showDialog.value
//                    } else if (field.selectCoord.value == cell.coord) {
//                        field.selectCoord.value = 0
//                        field.showDialog.value = !field.showDialog.value
//                    }
                    onClick()
                })
        )
    }
}


@Composable
fun ReverseButtonVSFriend1(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.return_icon),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}

@Composable
fun ReverseButtonVSFriend2(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.return_icon),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}

@Composable
fun PauseButtonVSFriend(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.pause),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}

@Composable
fun DrawButtonVSFriend1(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.draw),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(top = 3.dp)
        )
    }
}

@Composable
fun DrawButtonVSFriend2(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.draw),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(top = 3.dp)
        )
    }
}

@Composable
fun DefeatButtonVSFriend1(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.defeat),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}

@Composable
fun DefeatButtonVSFriend2(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.defeat),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}

@Composable
fun AcceptButtonVSFriend(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.accept),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}

@Composable
fun RefuseButtonVSFriend(onSettingsClick: () -> Unit) {
    IconButton(onClick = onSettingsClick) {
        Icon(
            ImageVector.vectorResource(R.drawable.refuse),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f)
        )
    }
}


@Composable
fun PauseDialogVSFriend(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Text(
                    text = "Игра поставлена на паузу",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onConfirm() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Вернуться в игру",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DrawDialogVSFriend1(
    onRefuse: () -> Unit,
    onConfirm: () -> Unit
) {

    Dialog(onDismissRequest = { onRefuse() }) {
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(15.dp)
                    .rotate(180f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(25.dp),
            ) {

                Text(
                    text = "Игрок 1 предлагает ничью",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 55.dp),
                    horizontalArrangement = Arrangement.spacedBy(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AcceptButtonVSFriend(onSettingsClick = {
                        onConfirm()
                    })
                    RefuseButtonVSFriend(onSettingsClick = {
                        onRefuse()
                    })
                }
            }
        }
    }
}

@Composable
fun DrawDialogVSFriend2(
    onRefuse: () -> Unit,
    onConfirm: () -> Unit
) {

    Dialog(onDismissRequest = { onRefuse() }) {
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Text(
                    text = "Игрок 2 предлагает ничью",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 55.dp),
                    horizontalArrangement = Arrangement.spacedBy(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AcceptButtonVSFriend(onSettingsClick = {
                        onConfirm()
                    })
                    RefuseButtonVSFriend(onSettingsClick = {
                        onRefuse()
                    })
                }
            }
        }
    }
}

@Composable
fun DefeatDialogVSFriend1(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(15.dp)
                    .rotate(180f),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Text(
                    text = "Игрок 1 сдался! Вы победили!",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onConfirm() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Вернуться в начало",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DefeatDialogVSFriend2(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Text(
                    text = "Игрок 2 сдался! Вы победили!",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onConfirm() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Вернуться в начало",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
