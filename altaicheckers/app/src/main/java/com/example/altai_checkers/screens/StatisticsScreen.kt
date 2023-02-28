package com.example.altai_checkers.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.altai_checkers.R
import com.example.altai_checkers.items.BackButton

@Composable
fun StatisticsScreen(navController: NavHostController) {

    // Тестовые данные
    class Game(val player1: String, val player2: String, val score: String)
    val testData = listOf<Game>(
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Игрок 2", "0:2"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Игрок 2", "0:2"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Игрок 2", "0:2"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Игрок 2", "0:2"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Компьютер", "Ничья"),
        Game("Игрок 1", "Игрок 2", "0:2"),
        Game("Игрок 1", "Компьютер", "Ничья"),
    )
    Row(horizontalArrangement = Arrangement.Start){
        BackButton(navController)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxSize()
                              .padding(8.dp)) {
        Text(stringResource(R.string.statistics), fontSize = 48.sp, lineHeight = 50.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text="Победы: 0 (0%)",
                fontSize = 18.sp
            )
            Text(
                text="Ничьи: 0 (0%)",
                fontSize = 18.sp
            )
            Text(
                text="Поражения: 0 (0%)",
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                ) {
                    Cell(Modifier.weight(2f), "Игрок 1", FontWeight.Bold)
                    Cell(Modifier.weight(2f),"Игрок 2", FontWeight.Bold)
                    Cell(Modifier.weight(1f),"Счёт", FontWeight.Bold)
                }
            }

            items(testData.size) {
                val index = it
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                ) {
                    Cell(Modifier.weight(2f), testData[index].player1, FontWeight.Normal)
                    Cell(Modifier.weight(2f), testData[index].player2, FontWeight.Normal)
                    Cell(Modifier.weight(1f), testData[index].score, FontWeight.Normal)
                }
            }
        }
    }
}

@Composable
fun Cell(modifier: Modifier, text: String, fontWeight: FontWeight) {
    Text(
        text = text,
        modifier = modifier
            .border(1.dp, Color.Gray)
            .padding(8.dp),
        fontWeight = fontWeight,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}