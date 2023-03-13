package com.example.altai_checkers.items

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class Game():
    ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    init {
        _uiState.value = GameUIState()
    }
    private var isStart = false

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun Start(){
        if (!isStart){
            _uiState.value.field.CreateField()
            isStart = true
        }

    }

    fun getField(): Field{
        return this._uiState.value.field
    }
}