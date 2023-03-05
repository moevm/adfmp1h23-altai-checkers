package com.example.altai_checkers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.navigation.NavHostController
import com.example.altai_checkers.R
import com.example.altai_checkers.ui.theme.BlackCell
import com.example.altai_checkers.ui.theme.WhiteCell

@Composable
fun GameVsBotScreen(navController: NavHostController) {
    var pauseState by remember { mutableStateOf(false) }
    var helpState by remember { mutableStateOf(false) }
    var drawState by remember { mutableStateOf(false) }
    var defeatState by remember { mutableStateOf(false) }
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

    class Cell(val fill: Color, val border: Color, val figure: ImageVector?)
    class Line(val cell1: Cell, val cell2: Cell, val cell3: Cell, val cell4: Cell, val cell5: Cell, val cell6: Cell, val cell7: Cell)
    val testData = listOf<Line>(
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_queen)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_rook)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_bishop)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_rook)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_bishop)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_king)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.black_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.black_pawn))),
        Line(Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null)),
        Line(Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null)),
        Line(Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null)),
        Line(Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null), Cell(WhiteCell, WhiteCell, null), Cell(BlackCell, BlackCell, null)),
        Line(Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_pawn))),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_king)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_bishop)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_rook)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_bishop)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_rook)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(BlackCell, BlackCell, ImageVector.vectorResource(R.drawable.white_queen)), Cell(WhiteCell, WhiteCell, ImageVector.vectorResource(R.drawable.white_pawn)), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background, null)),
    )
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
                items(testData.size) {
                    val index = it
                    Row(modifier = Modifier
                    ) {
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell1.fill, testData[index].cell1.figure, height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell2.fill, testData[index].cell2.figure, height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell3.fill, testData[index].cell3.figure, height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell4.fill, testData[index].cell4.figure, height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell5.fill, testData[index].cell5.figure, height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell6.fill, testData[index].cell6.figure, height)
                        FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell7.fill, testData[index].cell7.figure, height)
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
                navController.navigate("MainScreen")
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
fun FieldCell(modifier: Modifier, fontWeight: FontWeight, color: Color, figure: ImageVector?, height: Dp) {
    if (figure === null){
        Text(
            text = "",
            modifier = modifier
                .height(height / (165 / 10))
                .background(color),
            fontWeight = fontWeight
        )
    }
    else {
        Image(imageVector = figure, contentDescription = "Фигура",
            modifier = modifier
                .height(height / (165 / 10))
                .background(color))
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

