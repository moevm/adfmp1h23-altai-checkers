package com.example.altai_checkers.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.altai_checkers.R
import com.example.altai_checkers.ui.theme.BlackCell
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

open class Field {
    private lateinit var cells: List<Cell>
    private var allWhiteFortressFiguresPlayed = false // менять у фигур startFortress на false, как выставили из крепости
    private var allBlackFortressFiguresPlayed = false
    private var emptyWhiteFortress = false
    private var emptyBlackFortress = false
    @Composable
    fun CreateField(){
            this.cells = listOf(
                Cell(0, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(1, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(2, "cell", BlackCell, Figure("black_pawn", true, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(3, "cell", WhiteCell, Figure("black_queen", true, ImageVector.vectorResource(R.drawable.black_queen))),
                Cell(4, "cell", BlackCell, Figure("black_pawn", true, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(5, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(6, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(7, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(8, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(9, "cell", WhiteCell, Figure("black_pawn", true, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(10, "cell", BlackCell, Figure("black_rook", true, ImageVector.vectorResource(R.drawable.black_rook))),
                Cell(11, "cell", WhiteCell, Figure("black_pawn", true, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(12, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(13, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(14, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(15, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(16, "cell", BlackCell, Figure("black_bishop", true, ImageVector.vectorResource(R.drawable.black_bishop))),
                Cell(17, "cell", WhiteCell, Figure("black_rook", true, ImageVector.vectorResource(R.drawable.black_rook))),
                Cell(18, "cell", BlackCell, Figure("black_bishop", true, ImageVector.vectorResource(R.drawable.black_bishop))),
                Cell(19, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(20, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(21, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(22, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(23, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(24, "cell", BlackCell, Figure("black_king", true, ImageVector.vectorResource(R.drawable.black_king))),
                Cell(25, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(26, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(27, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(28, "cell", BlackCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(29, "cell", WhiteCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(30, "cell", BlackCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(31, "cell", WhiteCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(32, "cell", BlackCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(33, "cell", WhiteCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(34, "cell", BlackCell, Figure("black_pawn", false, ImageVector.vectorResource(R.drawable.black_pawn))),
                Cell(35, "cell", WhiteCell, Figure(null, false, null)),
                Cell(36, "cell", BlackCell, Figure(null, false, null)),
                Cell(37, "cell", WhiteCell, Figure(null, false, null)),
                Cell(38, "cell", BlackCell, Figure(null, false, null)),
                Cell(39, "cell", WhiteCell, Figure(null, false, null)),
                Cell(40, "cell", BlackCell, Figure(null, false, null)),
                Cell(41, "cell", WhiteCell, Figure(null, false, null)),
                Cell(42, "cell", BlackCell, Figure(null, false, null)),
                Cell(43, "cell", WhiteCell, Figure(null, false, null)),
                Cell(44, "cell", BlackCell, Figure(null, false, null)),
                Cell(45, "cell", WhiteCell, Figure(null, false, null)),
                Cell(46, "cell", BlackCell, Figure(null, false, null)),
                Cell(47, "cell", WhiteCell, Figure(null, false, null)),
                Cell(48, "cell", BlackCell, Figure(null, false, null)),
                Cell(49, "cell", WhiteCell, Figure(null, false, null)),
                Cell(50, "cell", BlackCell, Figure(null, false, null)),
                Cell(51, "cell", WhiteCell, Figure(null, false, null)),
                Cell(52, "cell", BlackCell, Figure(null, false, null)),
                Cell(53, "cell", WhiteCell, Figure(null, false, null)),
                Cell(54, "cell", BlackCell, Figure(null, false, null)),
                Cell(55, "cell", WhiteCell, Figure(null, false, null)),
                Cell(56, "cell", BlackCell, Figure(null, false, null)),
                Cell(57, "cell", WhiteCell, Figure(null, false, null)),
                Cell(58, "cell", BlackCell, Figure(null, false, null)),
                Cell(59, "cell", WhiteCell, Figure(null, false, null)),
                Cell(60, "cell", BlackCell, Figure(null, false, null)),
                Cell(61, "cell", WhiteCell, Figure(null, false, null)),
                Cell(62, "cell", BlackCell, Figure(null, false, null)),
                Cell(63, "cell", WhiteCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(64, "cell", BlackCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(65, "cell", WhiteCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(66, "cell", BlackCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(67, "cell", WhiteCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(68, "cell", BlackCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(69, "cell", WhiteCell, Figure("white_pawn", false, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(70, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(71, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(72, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(73, "cell", WhiteCell, Figure("white_king", true, ImageVector.vectorResource(R.drawable.white_king))),
                Cell(74, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(75, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(76, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(77, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(78, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(79, "cell", WhiteCell, Figure("white_bishop", true, ImageVector.vectorResource(R.drawable.white_bishop))),
                Cell(80, "cell", BlackCell, Figure("white_rook", true, ImageVector.vectorResource(R.drawable.white_rook))),
                Cell(81, "cell", WhiteCell, Figure("white_bishop", true, ImageVector.vectorResource(R.drawable.white_bishop))),
                Cell(82, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(83, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(84, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(85, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(86, "cell", BlackCell, Figure("white_pawn", true, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(87, "cell", WhiteCell, Figure("white_rook", true, ImageVector.vectorResource(R.drawable.white_rook))),
                Cell(88, "cell", BlackCell, Figure("white_pawn", true, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(89, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(90, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(91, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(92, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(93, "cell", WhiteCell, Figure("white_pawn", true, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(94, "cell", BlackCell, Figure("white_queen", true, ImageVector.vectorResource(R.drawable.white_queen))),
                Cell(95, "cell", WhiteCell, Figure("white_pawn", true, ImageVector.vectorResource(R.drawable.white_pawn))),
                Cell(96, "background", MaterialTheme.colorScheme.background, Figure(null, false, null)),
                Cell(97, "background",  MaterialTheme.colorScheme.background, Figure(null, false, null))
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
}

