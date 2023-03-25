package com.example.altai_checkers.viewmodels

import androidx.lifecycle.ViewModel
import com.example.altai_checkers.data.GameSettings
import com.example.altai_checkers.states.MainScreenState
import io.realm.Realm
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
        if (value != null && value >= 0) time = value
        _uiState.update{MainScreenState -> MainScreenState.copy(time = time)}
    }

    fun updateAdditionTime(value: Int?) {
        var additionTime: Int = 0
        if (value != null && value >= 0) additionTime = value
        _uiState.update{MainScreenState -> MainScreenState.copy(additionTime = additionTime)}
    }

    fun setError(error: String) {
        _uiState.update{MainScreenState -> MainScreenState.copy(error = error)}
    }

    fun checkBeforeStart(gamemode: String, onPass: () -> Unit, onError: () -> Unit) {
        if (_uiState.value.time == 0) setError("Установите время, отличное от нуля")
        if (_uiState.value.firstPlayer == "") setError("Имя пользователя не может быть пустым")
        if (gamemode == "friend") {
            if (_uiState.value.firstPlayer == _uiState.value.secondPlayer)
                setError("Введите разные имена пользователей")
            if (_uiState.value.secondPlayer == "") setError("Имя пользователя не может быть пустым")
        }
        if (gamemode == "bot")
            if (_uiState.value.firstPlayer == "Computer")
                setError("Имя пользователя \"Computer\" занято. Используйте другое имя пользователя")
        if (_uiState.value.withAddition)
            if (_uiState.value.additionTime == 0) setError("Установите добавочное время, отличное от нуля")

        if (_uiState.value.error != "") onError()
        else onPass()
    }

    fun writeSettingsToDatabase() {
        Realm.getDefaultInstance().executeTransaction() { realm ->
            val settings = realm.where(GameSettings::class.java).findFirst()
            if (settings != null) {
                settings.player1 = _uiState.value.firstPlayer
                settings.player2 = _uiState.value.secondPlayer
                settings.time = _uiState.value.time
                settings.addition = if (_uiState.value.withAddition) _uiState.value.additionTime else 0
            }
            else {
                realm.copyToRealm(
                    GameSettings(
                        _uiState.value.firstPlayer,
                        _uiState.value.secondPlayer,
                        _uiState.value.time,
                        if (_uiState.value.withAddition) _uiState.value.additionTime else 0
                    )
                )
            }
        }
    }
}