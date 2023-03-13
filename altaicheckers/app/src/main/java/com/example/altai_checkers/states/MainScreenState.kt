package com.example.altai_checkers.states

data class MainScreenState(
    val firstPlayer: String = "player1",
    val secondPlayer: String = "player2",
    val withAddition: Boolean = false,
    val time: Int = 20,
    val additionTime: Int = 5,
    val error: String = ""
)