package com.example.altai_checkers.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    class Cell(val fill: Color, val border: Color)
    class Line(val cell1: Cell, val cell2: Cell, val cell3: Cell, val cell4: Cell, val cell5: Cell, val cell6: Cell, val cell7: Cell)
    val testData = listOf<Line>(
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(BlackCell, BlackCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell)),
        Line(Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell)),
        Line(Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell)),
        Line(Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell)),
        Line(Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell)),
        Line(Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(WhiteCell, WhiteCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
        Line(Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(WhiteCell, WhiteCell), Cell(BlackCell, BlackCell), Cell(WhiteCell, WhiteCell), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background), Cell(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)),
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)) {
        /*Column( horizontalAlignment = Alignment.CenterHorizontally,
                ) {
            Row(horizontalArrangement = Arrangement.spacedBy(50.dp),
                modifier = Modifier
            ) {
                PauseButton(onSettingsClick = {
                    pauseState = true
                })
                HelpButton(onSettingsClick = {
                    helpState = true
                })
            }

        }*/
        Column( horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(1f)
        ) {
            Row(modifier = Modifier

            ) {
                Text(text = "Чёрные",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.width(170.dp)
                )
                Text(text = "0.5",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.width(100.dp)
                )
                Text(text = "02:58",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        LazyColumn(modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
        ) {
            items(testData.size) {
                val index = it
                Row(modifier = Modifier
                ) {
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell1.fill, testData[index].cell1.border)
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell2.fill, testData[index].cell2.border)
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell3.fill, testData[index].cell3.border)
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell4.fill, testData[index].cell4.border)
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell5.fill, testData[index].cell5.border)
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell6.fill, testData[index].cell6.border)
                    FieldCell(Modifier.weight(2f), FontWeight.Normal, testData[index].cell7.fill, testData[index].cell7.border)
                }
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Белые",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.width(170.dp)
                )
                Text(text = "0.5",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.width(100.dp)
                )
                Text(text = "02:39",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Column( horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(50.dp),
                modifier = Modifier
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
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun HelpButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.lamp),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun DrawButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.draw),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.95f).padding(top = 3.dp))}
}

@Composable
fun DefeatButton(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.defeat),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}


@Composable
fun FieldCell(modifier: Modifier, fontWeight: FontWeight, color: Color, border: Color) {
    Text(
        text = "",
        modifier = modifier
            .height(43.dp)
            .border(1.dp, border)
            .background(color),
        fontWeight = fontWeight
    )
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

                Text(text = "Игра поставлена на паузу")

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

                Text(text = "Лучший ход в данной позиции:")

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

                Text(text = "Компьютер не согласен на ничью")

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

                Text(text = "Вы сдались! Повезёт в следующий раз")

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {onConfirm()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        Text(text = "Вернуться в начальное меню",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center) } }
            }
        }
    }
}

