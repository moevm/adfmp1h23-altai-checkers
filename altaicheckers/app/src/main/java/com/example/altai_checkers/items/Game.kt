package com.example.altai_checkers.items

import androidx.compose.runtime.Composable

class Game(val gameType: String?, private val field: Field, val player1: String?, val player2: String?) {
    var isStart = false
    @Composable
    fun Start(){
        if(!isStart){
            println(111111)
            field.CreateField()
            isStart = true
        }
    }

    fun getField(): Field{
        return this.field
    }
}