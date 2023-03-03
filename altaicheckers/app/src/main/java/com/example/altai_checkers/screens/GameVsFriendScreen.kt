package com.example.altai_checkers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
fun GameVsFriendScreen(navController: NavHostController) {
    var pauseState by remember { mutableStateOf(false) }
    var drawState1 by remember { mutableStateOf(false) }
    var drawState2 by remember { mutableStateOf(false) }
    var defeatState1 by remember { mutableStateOf(false) }
    var defeatState2 by remember { mutableStateOf(false) }
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
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()) {
        Column( horizontalAlignment = Alignment.CenterHorizontally,
                ) {
            Row(horizontalArrangement = Arrangement.spacedBy(50.dp),
                modifier = Modifier
                    .rotate(180f)
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

        }
        Column( horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Row(modifier = Modifier
                .rotate(180f)
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
            .padding(start = 20.dp, end = 20.dp)
        ) {
            items(testData.size) {
                val index = it
                Row(modifier = Modifier
                ) {
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell1.fill, testData[index].cell1.border, testData[index].cell1.figure)
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell2.fill, testData[index].cell2.border, testData[index].cell2.figure)
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell3.fill, testData[index].cell3.border, testData[index].cell3.figure)
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell4.fill, testData[index].cell4.border, testData[index].cell4.figure)
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell5.fill, testData[index].cell5.border, testData[index].cell5.figure)
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell6.fill, testData[index].cell6.border, testData[index].cell6.figure)
                    FieldCellVSFriend(Modifier.weight(2f), FontWeight.Normal, testData[index].cell7.fill, testData[index].cell7.border, testData[index].cell7.figure)
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
                DrawButtonVSFriend1(onSettingsClick = {
                    drawState1 = true
                })
                DefeatButtonVSFriend1(onSettingsClick = {
                    defeatState1 = true
                })
                ReverseButtonVSFriend1(onSettingsClick = {

                })
                PauseButtonVSFriend (onSettingsClick = {
                    pauseState = true
                })
            }
        }
    }

    if (pauseState){
        PauseDialogVSFriend( onDismiss = { },
            onConfirm = {pauseState = false})
    }
    if (drawState1){
        DrawDialogVSFriend1( onRefuse = { drawState1 = false },
            onConfirm = {
                drawState1 = false
                navController.navigate("MainScreen")
            })
    }
    if (drawState2){
        DrawDialogVSFriend2( onRefuse = { drawState2 = false },
            onConfirm = {
                drawState2 = false
                navController.navigate("MainScreen")
            })
    }
    if (defeatState1){
        DefeatDialogVSFriend1( onDismiss = { },
            onConfirm = {
                defeatState1 = false
                navController.navigate("MainScreen")
            })
    }
    if (defeatState2){
        DefeatDialogVSFriend2( onDismiss = { },
            onConfirm = {
                defeatState2 = false
                navController.navigate("MainScreen")
            })
    }

}

@Composable
fun FieldCellVSFriend(modifier: Modifier, fontWeight: FontWeight, color: Color, border: Color, figure: ImageVector?) {
    if (figure === null){
        Text(
            text = "",
            modifier = modifier
                .height(40.dp)
                .border(1.dp, border)
                .background(color),
            fontWeight = fontWeight
        )
    }
    else {
        Image(imageVector = figure, contentDescription = "Фигура",
            modifier = modifier
                .height(40.dp)
                .border(1.dp, border)
                .background(color))
    }
}


@Composable
fun ReverseButtonVSFriend1(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.return_icon),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun ReverseButtonVSFriend2(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.return_icon),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun PauseButtonVSFriend(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.pause),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun DrawButtonVSFriend1(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.draw),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(top = 3.dp))}
}

@Composable
fun DrawButtonVSFriend2(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.draw),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(top = 3.dp))}
}

@Composable
fun DefeatButtonVSFriend1(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.defeat),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun DefeatButtonVSFriend2(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.defeat),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun AcceptButtonVSFriend(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.accept),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}

@Composable
fun RefuseButtonVSFriend(onSettingsClick: () -> Unit){
    IconButton(onClick = onSettingsClick){
        Icon(ImageVector.vectorResource(R.drawable.refuse),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier.fillMaxSize(0.75f))}
}



@Composable
fun PauseDialogVSFriend(   onDismiss: () -> Unit,
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
fun DrawDialogVSFriend1(   onRefuse: () -> Unit,
                  onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onRefuse() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp)
                .rotate(180f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(25.dp),){

                Text(text = "Игрок 1 предлагает ничью",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                    )

                Row(modifier = Modifier.fillMaxWidth().padding(start = 55.dp),
                horizontalArrangement = Arrangement.spacedBy(50.dp),
                verticalAlignment = Alignment.CenterVertically) {
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
fun DrawDialogVSFriend2(   onRefuse: () -> Unit,
                           onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onRefuse() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Игрок 2 предлагает ничью",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(modifier = Modifier.fillMaxWidth().padding(start = 55.dp),
                    horizontalArrangement = Arrangement.spacedBy(50.dp),
                    verticalAlignment = Alignment.CenterVertically) {
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
fun DefeatDialogVSFriend1(   onDismiss: () -> Unit,
                    onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp)
                .rotate(180f),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Игрок 1 сдался! Вы победили!",
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

@Composable
fun DefeatDialogVSFriend2(   onDismiss: () -> Unit,
                             onConfirm: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }){
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){

                Text(text = "Игрок 2 сдался! Вы победили!",
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
