package com.example.altai_checkers.states

data class StatisticEntry(
    val firstPlayer: String = "",
    val secondPlayer: String = "",
    val score: String = ""
)

data class StatisticsScreenState(
    val entries: List<StatisticEntry> = listOf(StatisticEntry(firstPlayer = "Player", secondPlayer = "Computer", score = "2:0")),
    val winCount: Int = 1,
    val drawCount: Int = 0,
    val loseCount: Int = 0
)