package com.example.altai_checkers.items


import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.altai_checkers.states.GameUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class Game():
    ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow()

    private var countDown1: CountDownTimer? = null
    private var countDown2: CountDownTimer? = null
    var activeTimerNumber: Int = 0
    private var isStart = false
    
    init {
        _uiState.value.field.createField()
        //_uiState.value = GameUIState()
        isStart = true
        startTimer1()
    }

    fun getField(): Field{
        return this._uiState.value.field
    }

    fun startTimer1() {
        countDown1 = object : CountDownTimer(_uiState.value.totalTime1 * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.update { GameUIState -> GameUIState.copy(totalTime1 = (millisUntilFinished / 1000).toInt()) }
            }

            override fun onFinish() {
                _uiState.update { GameUIState -> GameUIState.copy(totalTime1 = 0) }
            }
        }
        countDown1?.start()
        activeTimerNumber = 1
    }

    fun startTimer2() {
        countDown2 = object : CountDownTimer(_uiState.value.totalTime2 * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.update { GameUIState -> GameUIState.copy(totalTime2 = (millisUntilFinished / 1000).toInt()) }
            }

            override fun onFinish() {
                _uiState.update { GameUIState -> GameUIState.copy(totalTime2 = 0) }
            }
        }
        countDown2?.start()
        activeTimerNumber = 2
    }

    fun switchActiveTimer() {
        pauseTimer(activeTimerNumber)
        increaseTimer(activeTimerNumber)
        when(activeTimerNumber) {
            1 -> startTimer2()
            2 -> startTimer1()
        }
    }

    fun pauseTimer(timerNumber: Int?) {
        when(timerNumber) {
            1 -> countDown1?.cancel()
            2 -> countDown2?.cancel()
        }
    }

    fun increaseTimer(timerNumber: Int) {
        when(timerNumber) {
            1 -> _uiState.update { GameUIState -> GameUIState.copy(totalTime1 = _uiState.value.totalTime1 + _uiState.value.additionTime) }
            2 -> _uiState.update { GameUIState -> GameUIState.copy(totalTime2 = _uiState.value.totalTime2 + _uiState.value.additionTime) }
        }
    }

}