package com.example.altai_checkers

import android.content.Context
import com.example.altai_checkers.items.Cell
import com.example.altai_checkers.items.Field
import com.example.altai_checkers.items.Figure
import com.example.altai_checkers.items.Game
import com.example.altai_checkers.ui.theme.BlackCell
import com.example.altai_checkers.ui.theme.Theme
import com.example.altai_checkers.ui.theme.WhiteCell
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.internal.RealmCore
import io.realm.log.RealmLog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
@PowerMockIgnore(
    "org.mockito.*",
    "org.robolectric.*",
    "android.*",
    "jdk.internal.reflect.*",
    "androidx.*"
)
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(
    Realm::class,
    RealmConfiguration::class,
    RealmQuery::class,
    RealmResults::class,
    RealmCore::class,
    RealmLog::class
)
class StartGameTest {
    @Rule
    var rule = PowerMockRule()
    private var mockRealm: Realm? = null

    @Before
    fun setup() {
        PowerMockito.mockStatic(RealmCore::class.java)
        PowerMockito.mockStatic(RealmLog::class.java)
        PowerMockito.mockStatic(Realm::class.java)
        PowerMockito.mockStatic(RealmConfiguration::class.java)
        Realm.init(RuntimeEnvironment.application)
        PowerMockito.doNothing().`when`(RealmCore::class.java)
        RealmCore.loadLibrary(ArgumentMatchers.any(Context::class.java))
        val mockRealm = PowerMockito.mock(Realm::class.java)
        PowerMockito.`when`(Realm.getDefaultInstance()).thenReturn(mockRealm)
        this.mockRealm = mockRealm
    }

    private val game = Game()

    var field = Field()
    var correctCells = listOf(
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
    @Test
    fun checkStartPosition() {
        val gameField = game.getField()
        field.setCells(correctCells)
        assertTrue(gameField == field)
    }

    @Test
    fun checkPossiblePosition() {
        val correctCells1 = listOf(
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
            Cell(52, "cell", BlackCell, Figure(null, false, 11)),
            Cell(53, "cell", WhiteCell, Figure(null, false, 0)),
            Cell(54, "cell", BlackCell, Figure(null, false, 0)),
            Cell(55, "cell", WhiteCell, Figure(null, false, 0)),
            Cell(56, "cell", BlackCell, Figure(null, false, 0)),
            Cell(57, "cell", WhiteCell, Figure(null, false, 0)),
            Cell(58, "cell", BlackCell, Figure(null, false, 0)),
            Cell(59, "cell", WhiteCell, Figure(null, false, 11)),
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
        game.getField().setPossibleMovies(game.getField().getCells()[66].getPossibleMoveFields(game.getField()))
        val gameField = game.getField()
        field.setCells(correctCells1)
        assertTrue(gameField == field)
    }

    @Test
    fun checkWithoutChangePosition() {
        game.getField().setPossibleMovies(game.getField().getCells()[73].getPossibleMoveFields(game.getField()))
        val gameField = game.getField()
        field.setCells(correctCells)
        assertTrue(gameField == field)
    }

    @Test
    fun checkTimeAddition() {
        game.pauseActiveTimer()
        val timeBefore = game.uiState.value.time1
        game.increaseTimer(1)
        val diff = game.uiState.value.time1 - timeBefore
        assertEquals(diff, 5)
    }

    @Test
    fun checkTimerSwitch() {
        var activeTimer = game.activeTimerNumber
        assertEquals(activeTimer, 1)
        game.switchActiveTimer()
        activeTimer = game.activeTimerNumber
        game.pauseActiveTimer()
        assertEquals(activeTimer, 2)
    }
}
