package com.example.altai_checkers.states

import com.example.altai_checkers.items.Field

data class GameUIState(
    val gameType: String = "",
    var field: Field = Field(),
    val player1: String = "",
    val player2: String = "",
    val time1: Int = 20,
    val time2: Int = 20,
    val additionTime: Int = 5,
    val isGameEnd: Boolean = false,
    val gameEndMessage: String = ""
)