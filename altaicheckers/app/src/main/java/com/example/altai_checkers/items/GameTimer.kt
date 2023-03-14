package com.example.altai_checkers.items

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun GameTimer(timer: Int) {
    Row() {
        Text(getMinutes(timer).toString(), fontSize = 30.sp,)
        Text(":", fontSize = 30.sp,)
        Text("%02d".format(getSeconds(timer)), fontSize = 30.sp,)
    }
}

fun getMinutes(timer : Int) : Int {
    return timer / 60
}

fun getSeconds(timer : Int) : Int {
    return timer % 60
}