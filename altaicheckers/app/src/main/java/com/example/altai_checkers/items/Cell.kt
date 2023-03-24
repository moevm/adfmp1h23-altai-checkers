package com.example.altai_checkers.items

import androidx.compose.ui.graphics.Color

class Cell(val coord: Int, val cellType: String, val fill: Color, var figure: Figure) {


    fun getPossibleMoveFields(field: Field): MutableList<Int> {
        val possibleFields = mutableListOf<Int>()
        when (this.figure.type) {
            "white_pawn" -> {
                if (coord >= 73) {
                    for (i in 49..69){
                        if (field.getCells()[i].figure.type == null) possibleFields.add(i)
                    }
                }
                if (coord in 63..69){
                    if (field.getCells()[coord - 7].figure.type == null) possibleFields.add(coord - 7)
                    if (field.getCells()[coord - 14].figure.type == null) possibleFields.add(coord - 14)

                }
                if (coord in 56..62){
                    if (field.getCells()[coord - 7].figure.type == null) possibleFields.add(coord - 7)
                }
                if (coord in 49..55){
                    if (field.getCells()[coord - 7].figure.type == null) possibleFields.add(coord - 7)
                    if (field.getCells()[coord - 8].figure.type == null && coord - 8 >= 42) possibleFields.add(coord - 8)
                    if (field.getCells()[coord - 6].figure.type == null && coord - 6 <= 48) possibleFields.add(coord - 6)
                }
                if (coord in 42 .. 48){
                    if (field.getCells()[coord - 7].figure.type == null) possibleFields.add(coord - 7)
                    if (field.getCells()[coord - 8].figure.type == null && coord - 8 >= 35) possibleFields.add(coord - 8)
                    if (field.getCells()[coord - 6].figure.type == null && coord - 6 <= 41) possibleFields.add(coord - 6)
                    if (field.getCells()[coord + 1].figure.type == null && coord + 1 <= 48) possibleFields.add(coord - 8)
                    if (field.getCells()[coord - 1].figure.type == null && coord - 1 >= 42) possibleFields.add(coord - 6)
                }
                if (coord in 35 .. 41){
                    if (field.getCells()[coord - 7].figure.type == null) possibleFields.add(coord - 7)
                    if (field.getCells()[coord - 8].figure.type == null && coord - 8 >= 28) possibleFields.add(coord - 8)
                    if (field.getCells()[coord - 6].figure.type == null && coord - 6 <= 34) possibleFields.add(coord - 6)
                    if (field.getCells()[coord + 1].figure.type == null && coord + 1 <= 41) possibleFields.add(coord - 8)
                    if (field.getCells()[coord - 1].figure.type == null && coord - 1 >= 35) possibleFields.add(coord - 6)
                }
            }
        }
        return possibleFields
    }
}

//по найденным клеткам отображать возможные поля
//при переключении на др фигуру поля сбрасывать

