package com.example.altai_checkers.items

import androidx.compose.runtime.mutableStateOf
import com.example.altai_checkers.ui.theme.BlackCell
import com.example.altai_checkers.ui.theme.Theme
import com.example.altai_checkers.ui.theme.WhiteCell

/*
Игровое поле: * - игровые поля
0   1   2*  3*  4*  5   6
7   8   9*  10* 11* 12  13
14  15  16* 17* 18* 19  20
21  22  23  24* 25  26  27
28* 29* 30* 31* 32* 33* 34*
35* 36* 37* 38* 39* 40* 41*
42* 43* 44* 45* 46* 47* 48*
49* 50* 51* 52* 53* 54* 55*
56* 57* 58* 59* 60* 61* 62*
63* 64* 65* 66* 67* 68* 69*
70  71  72  73* 74  75  76
77  78  79* 80* 81* 82  83
84  85  86* 87* 88* 89  90
91  92  93* 94* 95* 96  97

 */

/*
0 - ничего
1 - черная пешка
2 - черный слон
3 - черная ладья
4 - черный ферзь
5 - черный король
6 - белая пешка
7 - белый слон
8 - белая ладья
9 - белый ферзь
10 - белый король
11 - точка
 */

class Field() {
    private lateinit var cells: List<Cell>
    private var allWhiteFortressFiguresPlayed = false // менять у фигур startFortress на false, как выставили из крепости
    private var allBlackFortressFiguresPlayed = false
    private var emptyWhiteFortress = false
    private var emptyBlackFortress = false
    val showDialog = mutableStateOf(false)
    private var selectFields: MutableList<Int> = mutableListOf()
    var selectCoord: Int = 0

    fun createField(){
            this.cells = listOf(
                Cell(0, "background",  Theme, Figure(null, false, 0)),
                Cell(1, "background", Theme, Figure(null, false, 0)),
                Cell(2, "cell", BlackCell, Figure("black_pawn", true, 1)),
                Cell(3, "cell", WhiteCell, Figure("black_queen", true, 4)),
                Cell(4, "cell", BlackCell, Figure("black_pawn", true, 1)),
                Cell(5, "background",  Theme, Figure(null, false, 0)),
                Cell(6, "background", Theme, Figure(null, false, 0)),
                Cell(7, "background",  Theme, Figure(null, false, 0)),
                Cell(8, "background", Theme, Figure(null, false, 0)),
                Cell(9, "cell", WhiteCell, Figure("black_pawn", true, 1)),
                Cell(10, "cell", BlackCell, Figure("black_rook", true, 3)),
                Cell(11, "cell", WhiteCell, Figure("black_pawn", true, 1)),
                Cell(12, "background",  Theme, Figure(null, false, 0)),
                Cell(13, "background", Theme, Figure(null, false, 0)),
                Cell(14, "background",  Theme, Figure(null, false, 0)),
                Cell(15, "background", Theme, Figure(null, false, 0)),
                Cell(16, "cell", BlackCell, Figure("black_bishop", true, 2)),
                Cell(17, "cell", WhiteCell, Figure("black_rook", true, 3)),
                Cell(18, "cell", BlackCell, Figure("black_bishop", true, 2)),
                Cell(19, "background",  Theme, Figure(null, false, 0)),
                Cell(20, "background", Theme, Figure(null, false, 0)),
                Cell(21, "background",  Theme, Figure(null, false, 0)),
                Cell(22, "background", Theme, Figure(null, false, 0)),
                Cell(23, "background",  Theme, Figure(null, false, 0)),
                Cell(24, "cell", BlackCell, Figure("black_king", true, 5)),
                Cell(25, "background",  Theme, Figure(null, false, 0)),
                Cell(26, "background", Theme, Figure(null, false, 0)),
                Cell(27, "background",  Theme, Figure(null, false, 0)),
                Cell(28, "cell", BlackCell, Figure("black_pawn", false, 1)),
                Cell(29, "cell", WhiteCell, Figure("black_pawn", false, 1)),
                Cell(30, "cell", BlackCell, Figure("black_pawn", false, 1)),
                Cell(31, "cell", WhiteCell, Figure("black_pawn", false, 1)),
                Cell(32, "cell", BlackCell, Figure("black_pawn", false, 1)),
                Cell(33, "cell", WhiteCell, Figure("black_pawn", false, 1)),
                Cell(34, "cell", BlackCell, Figure("black_pawn", false, 1)),
                Cell(35, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(36, "cell", BlackCell, Figure(null, false, 0)),
                Cell(37, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(38, "cell", BlackCell, Figure(null, false, 0)),
                Cell(39, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(40, "cell", BlackCell, Figure(null, false, 0)),
                Cell(41, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(42, "cell", BlackCell, Figure(null, false, 0)),
                Cell(43, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(44, "cell", BlackCell, Figure(null, false, 0)),
                Cell(45, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(46, "cell", BlackCell, Figure(null, false, 0)),
                Cell(47, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(48, "cell", BlackCell, Figure(null, false, 0)),
                Cell(49, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(50, "cell", BlackCell, Figure(null, false, 0)),
                Cell(51, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(52, "cell", BlackCell, Figure(null, false, 0)),
                Cell(53, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(54, "cell", BlackCell, Figure(null, false, 0)),
                Cell(55, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(56, "cell", BlackCell, Figure(null, false, 0)),
                Cell(57, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(58, "cell", BlackCell, Figure(null, false, 0)),
                Cell(59, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(60, "cell", BlackCell, Figure(null, false, 0)),
                Cell(61, "cell", WhiteCell, Figure(null, false, 0)),
                Cell(62, "cell", BlackCell, Figure(null, false, 0)),
                Cell(63, "cell", WhiteCell, Figure("white_pawn", false, 6)),
                Cell(64, "cell", BlackCell, Figure("white_pawn", false, 6)),
                Cell(65, "cell", WhiteCell, Figure("white_pawn", false, 6)),
                Cell(66, "cell", BlackCell, Figure("white_pawn", false, 6)),
                Cell(67, "cell", WhiteCell, Figure("white_pawn", false, 6)),
                Cell(68, "cell", BlackCell, Figure("white_pawn", false, 6)),
                Cell(69, "cell", WhiteCell, Figure("white_pawn", false, 6)),
                Cell(70, "background",  Theme, Figure(null, false, 0)),
                Cell(71, "background", Theme, Figure(null, false, 0)),
                Cell(72, "background",  Theme, Figure(null, false, 0)),
                Cell(73, "cell", WhiteCell, Figure("white_king", true, 10)),
                Cell(74, "background",  Theme, Figure(null, false, 0)),
                Cell(75, "background", Theme, Figure(null, false, 0)),
                Cell(76, "background",  Theme, Figure(null, false, 0)),
                Cell(77, "background", Theme, Figure(null, false, 0)),
                Cell(78, "background",  Theme, Figure(null, false, 0)),
                Cell(79, "cell", WhiteCell, Figure("white_bishop", true, 7)),
                Cell(80, "cell", BlackCell, Figure("white_rook", true, 8)),
                Cell(81, "cell", WhiteCell, Figure("white_bishop", true, 7)),
                Cell(82, "background", Theme, Figure(null, false, 0)),
                Cell(83, "background",  Theme, Figure(null, false, 0)),
                Cell(84, "background", Theme, Figure(null, false, 0)),
                Cell(85, "background",  Theme, Figure(null, false, 0)),
                Cell(86, "cell", BlackCell, Figure("white_pawn", true, 6)),
                Cell(87, "cell", WhiteCell, Figure("white_rook", true, 8)),
                Cell(88, "cell", BlackCell, Figure("white_pawn", true, 6)),
                Cell(89, "background", Theme, Figure(null, false, 0)),
                Cell(90, "background",  Theme, Figure(null, false, 0)),
                Cell(91, "background", Theme, Figure(null, false, 0)),
                Cell(92, "background",  Theme, Figure(null, false, 0)),
                Cell(93, "cell", WhiteCell, Figure("white_pawn", true, 6)),
                Cell(94, "cell", BlackCell, Figure("white_queen", true, 9)),
                Cell(95, "cell", WhiteCell, Figure("white_pawn", true, 6)),
                Cell(96, "background", Theme, Figure(null, false, 0)),
                Cell(97, "background",  Theme, Figure(null, false, 0))
            )

    }

    fun getCells(): List<Cell>{
        return this.cells
    }
    /* после каждого хода */
    fun checkEmptyFortress(){
        var blackFlag = false
        var whiteFlag = false
        for (i in arrayOf(2, 3, 4, 9, 10, 11, 16, 17, 18, 24)){
            if (cells[i].figure.type != null) blackFlag = true
            if (blackFlag){
                emptyBlackFortress = false
                break
            }
        }
        if (!blackFlag) emptyBlackFortress = true
        for (i in arrayOf(73, 79, 80, 81, 86, 87, 88, 93, 94, 95)){
            if (cells[i].figure.type != null) whiteFlag = true
            if (whiteFlag){
                emptyWhiteFortress = false
                break
            }
        }
        if (!whiteFlag) emptyWhiteFortress = true

    }

    fun isEmptyWhiteFortress(): Boolean{
        return emptyWhiteFortress
    }

    fun isEmptyBlackFortress(): Boolean{
        return emptyBlackFortress
    }

    /* проверять на каждом шаге */
    fun checkAllFortressFiguresPlayed(){
        var blackFlag = false
        var whiteFlag = false
        for (i in arrayOf(2, 3, 4, 9, 10, 11, 16, 17, 18, 24)){
            if (cells[i].figure.startFortress) blackFlag = true
            if (blackFlag){
                allBlackFortressFiguresPlayed = false
                break
            }
        }
        if (!blackFlag) allBlackFortressFiguresPlayed = true
        for (i in arrayOf(73, 79, 80, 81, 86, 87, 88, 93, 94, 95)){
            if (cells[i].figure.startFortress) whiteFlag = true
            if (whiteFlag){
                emptyWhiteFortress = false
                break
            }
        }
        if (!whiteFlag) allWhiteFortressFiguresPlayed = true

    }
    fun setPossibleMovies(fields: MutableList<Int>){
        this.selectFields = fields
        for (i in fields){
            cells[i].figure.figureId = 11
        }
    }
    fun unsetPossibleMovies(fields: MutableList<Int>){
        this.selectFields = mutableListOf()
        for (i in fields){
            cells[i].figure.figureId = 0
        }
    }

    fun getSelectFields(): MutableList<Int> {
        return this.selectFields
    }

    fun setCells(cells: List<Cell>){
        this.cells = cells
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that: Field = o as Field
        println(that.cells[0].coord)
        for (i in this.cells.indices){
            if (this.cells[i].coord != that.cells[i].coord) return false
            if (this.cells[i].cellType != that.cells[i].cellType) return false
            if (this.cells[i].fill != that.cells[i].fill) return false
            if (this.cells[i].figure.type != that.cells[i].figure.type) return false
            if (this.cells[i].figure.startFortress != that.cells[i].figure.startFortress) return false
            if (this.cells[i].figure.figureId != that.cells[i].figure.figureId) return false
        }
        return true
    }
}

