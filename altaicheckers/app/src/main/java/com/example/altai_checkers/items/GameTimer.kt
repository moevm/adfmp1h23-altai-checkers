package com.example.altai_checkers.items

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun GameTimer(durationTime: Int, additionTime: Int, isTimeRunning: Boolean) {
    var totalTime by remember {
        mutableStateOf(durationTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(isTimeRunning)
    }
    LaunchedEffect(key1 = totalTime, key2 = isTimerRunning) {
        if (totalTime > 0 && isTimerRunning) {
            delay(1000)
            totalTime -= 1
        }
    }
    Row() {
        Text((totalTime / 60).toString(), fontSize = 30.sp,)
        Text(":", fontSize = 30.sp,)
        Text("%02d".format(totalTime % 60), fontSize = 30.sp,)
    }
}