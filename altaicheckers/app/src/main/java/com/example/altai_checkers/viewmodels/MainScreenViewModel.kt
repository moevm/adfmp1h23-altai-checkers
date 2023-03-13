package com.example.altai_checkers.viewmodels

import androidx.lifecycle.ViewModel
import com.example.altai_checkers.states.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    init {
        _uiState.value = MainScreenState()
    }

    fun updatePlayerName(playerName: String, playerNumber: Int) {
        if (playerNumber == 1)
            _uiState.update{MainScreenState -> MainScreenState.copy(firstPlayer = playerName)}
        if (playerNumber == 2)
            _uiState.update{MainScreenState -> MainScreenState.copy(secondPlayer = playerName)}
    }

    fun setWithAddition(state: Boolean) {
        _uiState.update{MainScreenState -> MainScreenState.copy(withAddition = state)}
    }

    fun updateTime(value: Int?) {
        var time: Int = 0
        if (value != null) time = value
        _uiState.update{MainScreenState -> MainScreenState.copy(time = time)}
    }

    fun updateAdditionTime(value: Int?) {
        var additionTime: Int = 0
        if (value != null) additionTime = value
        _uiState.update{MainScreenState -> MainScreenState.copy(additionTime = additionTime)}
    }
}