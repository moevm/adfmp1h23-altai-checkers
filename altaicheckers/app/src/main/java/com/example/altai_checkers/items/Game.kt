package com.example.altai_checkers.items


import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.altai_checkers.data.GameSettings
import com.example.altai_checkers.states.GameUIState
import io.realm.Realm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class Game() :
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
        //isStart = true
        //startTimer1()
    }

    fun getField(): Field {
        return this._uiState.value.field
    }

    fun initSettings() {
        if (!isStart) {
            Realm.getDefaultInstance().executeTransaction { realm ->
                val settings = realm.where(GameSettings::class.java).findFirst()
                if (settings != null) {
                    _uiState.update { GameUIState ->
                        GameUIState.copy(
                            player1 = settings.player1,
                            player2 = settings.player2,
                            time1 = settings.time,
                            time2 = settings.time,
                            additionTime = settings.addition
                        )
                    }
                }
            }
            isStart = true
            startTimer1()
        }
    }

    fun startTimer1() {
        countDown1 = object : CountDownTimer(_uiState.value.time1 * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.update { GameUIState -> GameUIState.copy(time1 = (millisUntilFinished / 1000).toInt()) }
            }

            override fun onFinish() {
                _uiState.update { GameUIState -> GameUIState.copy(time1 = 0) }
            }
        }
        countDown1?.start()
        activeTimerNumber = 1
    }

    fun startTimer2() {
        countDown2 = object : CountDownTimer(_uiState.value.time2 * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.update { GameUIState -> GameUIState.copy(time2 = (millisUntilFinished / 1000).toInt()) }
            }

            override fun onFinish() {
                _uiState.update { GameUIState -> GameUIState.copy(time2 = 0) }
            }
        }
        countDown2?.start()
        activeTimerNumber = 2
    }

    fun switchActiveTimer() {
        pauseActiveTimer()
        increaseTimer(activeTimerNumber)
        when (activeTimerNumber) {
            1 -> startTimer2()
            2 -> startTimer1()
        }
    }

    fun pauseActiveTimer() {
        when (activeTimerNumber) {
            1 -> countDown1?.cancel()
            2 -> countDown2?.cancel()
        }
    }

    fun increaseTimer(timerNumber: Int) {
        when (timerNumber) {
            1 -> _uiState.update { GameUIState -> GameUIState.copy(time1 = _uiState.value.time1 + _uiState.value.additionTime) }
            2 -> _uiState.update { GameUIState -> GameUIState.copy(time2 = _uiState.value.time2 + _uiState.value.additionTime) }
        }
    }

}