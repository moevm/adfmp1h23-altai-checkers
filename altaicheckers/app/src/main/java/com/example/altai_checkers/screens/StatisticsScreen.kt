package com.example.altai_checkers.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.altai_checkers.viewmodels.StatisticsScreenViewModel

@Composable
fun StatisticsScreen(navController: NavHostController, viewModel: StatisticsScreenViewModel) {
    val uiState by viewModel.uiState.collectAsState()
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
                text=String.format("Победы: %d (%.2f%c)", uiState.winCount, viewModel.getPercentage("win"), '%'),
                fontSize = 18.sp
            )
            Text(
                text=String.format("Ничьи: %d (%.2f%c)", uiState.drawCount, viewModel.getPercentage("draw"), '%'),
                fontSize = 18.sp
            )
            Text(
                text=String.format("Поражения: %d (%.2f%c)", uiState.loseCount, viewModel.getPercentage("lose"), '%'),
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

            items(uiState.entries.size) {
                val index = it
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                ) {
                    Cell(Modifier.weight(2f), uiState.entries[index].firstPlayer, FontWeight.Normal)
                    Cell(Modifier.weight(2f), uiState.entries[index].secondPlayer, FontWeight.Normal)
                    Cell(Modifier.weight(1f), uiState.entries[index].score, FontWeight.Normal)
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