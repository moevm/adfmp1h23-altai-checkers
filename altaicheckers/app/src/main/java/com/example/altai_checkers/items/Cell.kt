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
                    if (field.getCells()[coord + 1].figure.type == null && coord + 1 <= 48) possibleFields.add(coord + 1)
                    if (field.getCells()[coord - 1].figure.type == null && coord - 1 >= 42) possibleFields.add(coord - 1)
                }
                if (coord in 35 .. 41){
                    if (field.getCells()[coord - 7].figure.type == null) possibleFields.add(coord - 7)
                    if (field.getCells()[coord - 8].figure.type == null && coord - 8 >= 28) possibleFields.add(coord - 8)
                    if (field.getCells()[coord - 6].figure.type == null && coord - 6 <= 34) possibleFields.add(coord - 6)
                    if (field.getCells()[coord + 1].figure.type == null && coord + 1 <= 41) possibleFields.add(coord + 1)
                    if (field.getCells()[coord - 1].figure.type == null && coord - 1 >= 35) possibleFields.add(coord - 1)
                }
            }
            "black_pawn" -> {
                if (coord <= 24) {
                    for (i in 28..48){
                        if (field.getCells()[i].figure.type == null) possibleFields.add(i)
                    }
                }
                if (coord in 28..34){
                    if (field.getCells()[coord + 7].figure.type == null) possibleFields.add(coord + 7)
                    if (field.getCells()[coord + 14].figure.type == null) possibleFields.add(coord + 14)

                }
                if (coord in 35..41){
                    if (field.getCells()[coord + 7].figure.type == null) possibleFields.add(coord + 7)
                }
                if (coord in 42..48){
                    if (field.getCells()[coord + 7].figure.type == null) possibleFields.add(coord + 7)
                    if (field.getCells()[coord + 8].figure.type == null && coord + 8 <= 55) possibleFields.add(coord + 8)
                    if (field.getCells()[coord + 6].figure.type == null && coord + 6 >= 49) possibleFields.add(coord + 6)
                }
                if (coord in 49 .. 55){
                    if (field.getCells()[coord + 7].figure.type == null) possibleFields.add(coord + 7)
                    if (field.getCells()[coord + 8].figure.type == null && coord + 8 <= 62) possibleFields.add(coord + 8)
                    if (field.getCells()[coord + 6].figure.type == null && coord + 6 >= 56) possibleFields.add(coord + 6)
                    if (field.getCells()[coord - 1].figure.type == null && coord - 1 >= 49) possibleFields.add(coord - 1)
                    if (field.getCells()[coord + 1].figure.type == null && coord + 1 <= 55) possibleFields.add(coord + 1)
                }
                if (coord in 56 .. 62){
                    if (field.getCells()[coord + 7].figure.type == null) possibleFields.add(coord + 7)
                    if (field.getCells()[coord + 8].figure.type == null && coord + 8 <= 69) possibleFields.add(coord + 8)
                    if (field.getCells()[coord + 6].figure.type == null && coord + 6 >= 63) possibleFields.add(coord + 6)
                    if (field.getCells()[coord - 1].figure.type == null && coord - 1 >= 56) possibleFields.add(coord - 1)
                    if (field.getCells()[coord + 1].figure.type == null && coord + 1 <= 62) possibleFields.add(coord + 1)
                }
            }
            "white_rook" -> {
                if (coord >= 73){ //добавить ход из своей крепости в чужую
                    for (i in 49..69){
                        if (field.getCells()[i].figure.type == null) possibleFields.add(i)
                    }
                }
                else {
                    var lineCoord = coord - 1
                    while (lineCoord >= coord / 7 * 7){
                        if (field.getCells()[lineCoord].cellType == "background") break
                        else if (field.getCells()[lineCoord].figure.type == null) possibleFields.add(lineCoord)
                        else break
                        lineCoord -= 1
                    }

                    lineCoord = coord + 1
                    while (lineCoord <= coord / 7 * 7 + 6){
                        if (field.getCells()[lineCoord].cellType == "background") break
                        else if (field.getCells()[lineCoord].figure.type == null) possibleFields.add(lineCoord)
                        else break
                        lineCoord += 1
                    }

                    var rowCoord = coord - 7
                    while (rowCoord > 0){
                        if (field.getCells()[rowCoord].cellType == "background") break
                        else if (field.getCells()[rowCoord].figure.type == null) possibleFields.add(rowCoord)
                        else break
                        rowCoord -= 7
                    }

                    rowCoord = coord + 7
                    while (rowCoord < 98){
                        if (field.getCells()[rowCoord].cellType == "background") break
                        else if (field.getCells()[rowCoord].figure.type == null) possibleFields.add(rowCoord)
                        else break
                        rowCoord += 7
                    }
                }
            }
            "black_rook" -> {
                if (coord <= 24){ //добавить ход из своей крепости в чужую
                    for (i in 28..48){
                        if (field.getCells()[i].figure.type == null) possibleFields.add(i)
                    }
                }
                else {
                    var lineCoord = coord - 1
                    while (lineCoord >= coord / 7 * 7){
                        if (field.getCells()[lineCoord].cellType == "background") break
                        else if (field.getCells()[lineCoord].figure.type == null) possibleFields.add(lineCoord)
                        else break
                        lineCoord -= 1
                    }

                    lineCoord = coord + 1
                    while (lineCoord <= coord / 7 * 7 + 6){
                        if (field.getCells()[lineCoord].cellType == "background") break
                        else if (field.getCells()[lineCoord].figure.type == null) possibleFields.add(lineCoord)
                        else break
                        lineCoord += 1
                    }

                    var rowCoord = coord - 7
                    while (rowCoord > 0){
                        if (field.getCells()[rowCoord].cellType == "background") break
                        else if (field.getCells()[rowCoord].figure.type == null) possibleFields.add(rowCoord)
                        else break
                        rowCoord -= 7
                    }

                    rowCoord = coord + 7
                    while (rowCoord < 98){
                        if (field.getCells()[rowCoord].cellType == "background") break
                        else if (field.getCells()[rowCoord].figure.type == null) possibleFields.add(rowCoord)
                        else break
                        rowCoord += 7
                    }
                }
            }
            "white_bishop" -> {
                if (coord >= 73){
                    for (i in 49..69){
                        if (field.getCells()[i].figure.type == null) possibleFields.add(i)
                    }
                }
                else {
                    var possibleCoord = coord - 6
                    while (possibleCoord % 7 != 0){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord -= 6
                    }

                    possibleCoord = coord - 8
                    while (possibleCoord % 7 != 6){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord -= 8
                    }

                    possibleCoord = coord + 6
                    while (possibleCoord % 7 != 6){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord += 6
                    }

                    possibleCoord = coord + 8
                    while (possibleCoord % 7 != 0){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord += 8
                    }
                }
            }
            "black_bishop" -> {
                if (coord <= 24){
                    for (i in 28..48){
                        if (field.getCells()[i].figure.type == null) possibleFields.add(i)
                    }
                }
                else {
                    var possibleCoord = coord - 6
                    while (possibleCoord % 7 != 0){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord -= 6
                    }

                    possibleCoord = coord - 8
                    while (possibleCoord % 7 != 6){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord -= 8
                    }

                    possibleCoord = coord + 6
                    while (possibleCoord % 7 != 6){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord += 6
                    }

                    possibleCoord = coord + 8
                    while (possibleCoord % 7 != 0){
                        if (field.getCells()[possibleCoord].cellType == "background") break
                        else if (field.getCells()[possibleCoord].figure.type == null) possibleFields.add(possibleCoord)
                        else break
                        possibleCoord += 8
                    }
                }
            }
        }
        return possibleFields
    }
}

//по найденным клеткам отображать возможные поля
//при переключении на др фигуру поля сбрасывать

