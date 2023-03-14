package com.example.altai_checkers.items

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class Game():
    ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    private var isStart = false
    init {
        _uiState.value.field.createField()
        //_uiState.value = GameUIState()
        isStart = true
    }

    fun getField(): Field{
        return this._uiState.value.field
    }
}