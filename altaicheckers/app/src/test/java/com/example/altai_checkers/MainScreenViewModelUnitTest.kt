package com.example.altai_checkers

import com.example.altai_checkers.viewmodels.MainScreenViewModel
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*

class MainScreenViewModelUnitTest {
    @Test
    fun updatePlayerNameTest() {
        val viewModel = MainScreenViewModel()
        viewModel.updatePlayerName("TestFirstName", 1)
        viewModel.updatePlayerName("TestSecondName", 2)

        val currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.firstPlayer, "TestFirstNam")
        TestCase.assertEquals(currentUiState.secondPlayer, "TestSecondName")
    }

    @Test
    fun setWithAdditionTest() {
        val viewModel = MainScreenViewModel()
        viewModel.setWithAddition(true)
        var currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.withAddition, true)

        viewModel.setWithAddition(false)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.withAddition, false)
    }

    @Test
    fun updateTimeTest() {
        val viewModel = MainScreenViewModel()
        viewModel.updateTime(100)
        var currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.time, 100)

        viewModel.updateTime(-1)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.time, 0)

        viewModel.updateTime(null)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.time, 0)

        viewModel.updateTime(10)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.time, 10)
    }

    @Test
    fun updateAdditionTimeTest() {
        val viewModel = MainScreenViewModel()
        viewModel.updateAdditionTime(10)
        var currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.additionTime, 10)

        viewModel.updateAdditionTime(-5)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.additionTime, 0)

        viewModel.updateAdditionTime(null)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.additionTime, 0)

        viewModel.updateAdditionTime(5)
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.additionTime, 5)
    }

    @Test
    fun setErrorTest() {
        val viewModel = MainScreenViewModel()
        viewModel.setError("Error")
        var currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Error")

        viewModel.setError("Another error")
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Another error")

        viewModel.setError("")
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "")
    }

    @Test
    fun checkBeforeStartTest() {
        var viewModel = MainScreenViewModel()
        viewModel.checkBeforeStart("bot", {}, {})
        var currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "")

        viewModel = MainScreenViewModel()
        viewModel.updateTime(0)
        viewModel.checkBeforeStart("bot", {}, {})
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Установите время, отличное от нуля")

        viewModel = MainScreenViewModel()
        viewModel.updatePlayerName("", 1)
        viewModel.checkBeforeStart("bot", {}, {})
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Имя пользователя не может быть пустым")

        viewModel = MainScreenViewModel()
        viewModel.updatePlayerName("Player", 1)
        viewModel.updatePlayerName("Player", 2)
        viewModel.checkBeforeStart("friend", {}, {})
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Введите разные имена пользователей")

        viewModel = MainScreenViewModel()
        viewModel.updatePlayerName("Computer", 1)
        viewModel.checkBeforeStart("bot", {}, {})
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Имя пользователя \"Computer\" занято. Используйте другое имя пользователя")

        viewModel = MainScreenViewModel()
        viewModel.updateAdditionTime(0)
        viewModel.checkBeforeStart("bot", {}, {})
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "")
        viewModel.setWithAddition(true)
        viewModel.checkBeforeStart("bot", {}, {})
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(currentUiState.error, "Установите добавочное время, отличное от нуля")
    }
}
