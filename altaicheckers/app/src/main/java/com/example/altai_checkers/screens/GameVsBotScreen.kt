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
import com.example.altai_checkers.items.Field
import com.example.altai_checkers.items.Game



@Composable
fun GameVsBotScreen(navController: NavHostController, game: Game = viewModel()) {
    var pauseState by remember { mutableStateOf(false) }
    var helpState by remember { mutableStateOf(false) }
    var drawState by remember { mutableStateOf(false) }
    var defeatState by remember { mutableStateOf(false) }
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    game.Start()
    if(game.getField().showDialog.value)
        game.getField().SetPossibleMovies(game.getField().getCells()[game.getField().selectCoord].getPossibleMoveFields(game.getField()))
    else{
        game.getField().UnsetPossibleMovies(game.getField().getSelectFields())
    }
    Column(modifier = Modifier
            .fillMaxSize()) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 23)
            ) {
                Text(
                    text = "Чёрные",
                    fontSize = 22.sp,
                )
                Text(
                    text = "0.5",
                    fontSize = 18.sp,
                )
                Text(
                    text = "02:58",
                    fontSize = 30.sp,
                )
            }
            LazyColumn(modifier = Modifier
                .padding(start = width / 40, end = width / 40)
            ) {
                items(game.getField().getCells().size / 7) {
                    val index = it
                    Row(modifier = Modifier
                    ) {
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7], game.getField(), height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7 + 1], game.getField(), height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7 + 2], game.getField(), height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7 + 3], game.getField(), height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7 + 4], game.getField(), height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7 + 5], game.getField(), height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, game.getField().getCells()[index * 7 + 6], game.getField(), height)
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
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
                Text(
                    text = "02:39",
                    fontSize = 30.sp,
                )
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 23)
            ) {
                DrawButton(onSettingsClick = {
                    drawState = true
                })
                DefeatButton(onSettingsClick = {
                    defeatState = true
                })
                PauseButton(onSettingsClick = {
                    pauseState = true
                })
                HelpButton(onSettingsClick = {
                    helpState = true
                })
            }
    }

    if (pauseState){
        PauseDialog( onDismiss = { },
                     onConfirm = {pauseState = false})
    }
    if (helpState){
        HelpDialog( onDismiss = { },
                    onConfirm = {helpState = false})
    }
    if (drawState){
        DrawDialog( onDismiss = { },
            onConfirm = {drawState = false})
    }
    if (defeatState){
        DefeatDialog( onDismiss = { },
            onConfirm = {
                defeatState = false
                navController.popBackStack()
            })
    }

}

@Composable
fun PauseButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.pause),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.75f)
                .size(128.dp),

        )}
}

@Composable
fun HelpButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.lamp),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.75f)
                .size(128.dp))}
}

@Composable
fun DrawButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.draw),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(top = 3.dp)
                .size(128.dp))}
}

@Composable
fun DefeatButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.defeat),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.75f)
                .size(128.dp))}
}


@Composable
fun FieldCell(modifier: Modifier, fontWeight: FontWeight, cell: Cell, field: Field, height: Dp) {
    if (cell.figure.imageVector === null){
        Text(
            text = "",
            modifier = modifier
                .height(height / (165 / 10))
                .background(cell.fill),
            fontWeight = fontWeight
        )
    }
    else {
        Image(imageVector = cell.figure.imageVector!!, contentDescription = "Фигура",
            modifier = modifier
                .height(height / (165 / 10))
                .background(cell.fill)
                .clickable(onClick = {
                    if (field.selectCoord == 0) {
                        field.selectCoord = cell.coord
                        field.showDialog.value = !field.showDialog.value
                    } else if (field.selectCoord == cell.coord) {
                        field.selectCoord = 0
                        field.showDialog.value = !field.showDialog.value
                    }
                })
        )
    }
}

@Composable
fun PauseDialog(   onDismiss: () -> Unit,
                   onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Игра поставлена на паузу",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {onConfirm()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        Text(text = "Вернуться в игру",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center) } }
            }
        }
    }
}
@Composable
fun HelpDialog(   onDismiss: () -> Unit,
                   onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Лучший ход в данной позиции:",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {onConfirm()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        Text(text = "Вернуться в игру",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center) } }
            }
        }
    }
}

@Composable
fun DrawDialog(   onDismiss: () -> Unit,
                   onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Компьютер не согласен на ничью",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )


                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {onConfirm()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        Text(text = "Вернуться в игру",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center) } }
            }
        }
    }
}
@Composable
fun DefeatDialog(   onDismiss: () -> Unit,
                  onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Вы сдались! Повезёт в следующий раз",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )


                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {onConfirm()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        Text(text = "Вернуться в начало",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center) } }
            }
        }
    }
}

