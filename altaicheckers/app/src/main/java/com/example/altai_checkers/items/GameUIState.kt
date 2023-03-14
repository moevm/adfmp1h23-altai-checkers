package com.example.altai_checkers.items

data class GameUIState(
    val gameType: String = "",
    var field: Field = Field(),
    val player1: String = "",
    val player2: String = "",
    val totalTime1: Int = 20,
    val totalTime2: Int = 20,
    val additionTime: Int = 5
) {

}