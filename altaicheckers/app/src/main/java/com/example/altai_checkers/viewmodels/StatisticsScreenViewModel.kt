package com.example.altai_checkers.viewmodels

import androidx.lifecycle.ViewModel
import com.example.altai_checkers.states.MainScreenState
import com.example.altai_checkers.states.StatisticEntry
import com.example.altai_checkers.states.StatisticsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StatisticsScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(StatisticsScreenState())
    val uiState: StateFlow<StatisticsScreenState> = _uiState.asStateFlow()

    init {
        _uiState.value = StatisticsScreenState()
    }

    fun addEntry(firstPlayer: String, secondPlayer: String, score: String, result: String) {
        val newEntry = StatisticEntry(firstPlayer, secondPlayer, score)
        _uiState.update{StatisticsScreenState ->
            StatisticsScreenState.copy(entries = _uiState.value.entries + newEntry)
        }
        if (result == "win")
            _uiState.update { StatisticsScreenState ->
                StatisticsScreenState.copy(winCount = _uiState.value.winCount + 1)
            }
        if (result == "draw")
            _uiState.update { StatisticsScreenState ->
                StatisticsScreenState.copy(drawCount = _uiState.value.drawCount + 1)
            }
        if (result == "lose")
            _uiState.update { StatisticsScreenState ->
                StatisticsScreenState.copy(loseCount = _uiState.value.loseCount + 1)
            }
    }

    fun getPercentage(result: String): Float {
        val totalGames = (_uiState.value.winCount + _uiState.value.drawCount + _uiState.value.loseCount).toFloat()
        if (result == "win")
            return (_uiState.value.winCount / totalGames) * 100f
        if (result == "draw")
            return (_uiState.value.drawCount / totalGames) * 100f
        if (result == "lose")
            return (_uiState.value.loseCount / totalGames) * 100f
        return 0f
    }
}