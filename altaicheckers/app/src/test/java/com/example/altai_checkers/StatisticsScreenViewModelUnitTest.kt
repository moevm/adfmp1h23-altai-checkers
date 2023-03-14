package com.example.altai_checkers

import com.example.altai_checkers.states.StatisticEntry
import com.example.altai_checkers.viewmodels.StatisticsScreenViewModel
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*

class StatisticsScreenViewModelUnitTest {
    @Test
    fun addEntryTest() {
        val viewModel = StatisticsScreenViewModel()
        viewModel.addEntry("TestFirstName", "TestSecondName", "0:1", "lose")
        var currentUiState = viewModel.uiState.value
        TestCase.assertEquals(
            currentUiState.entries,
            listOf(
                StatisticEntry(firstPlayer = "Player", secondPlayer = "Computer", score = "2:0"),
                StatisticEntry(firstPlayer = "TestFirstName", secondPlayer = "TestSecondName", score = "0:1")
            )
        )
        TestCase.assertEquals(currentUiState.winCount, 1)
        TestCase.assertEquals(currentUiState.drawCount, 0)
        TestCase.assertEquals(currentUiState.loseCount, 1)

        viewModel.addEntry("FirstName", "SecondName", "1:1", "draw")
        currentUiState = viewModel.uiState.value
        TestCase.assertEquals(
            currentUiState.entries,
            listOf(
                StatisticEntry(firstPlayer = "Player", secondPlayer = "Computer", score = "2:0"),
                StatisticEntry(firstPlayer = "TestFirstName", secondPlayer = "TestSecondName", score = "0:1"),
                StatisticEntry(firstPlayer = "FirstName", secondPlayer = "SecondName", score = "1:1")
            )
        )
        TestCase.assertEquals(currentUiState.winCount, 1)
        TestCase.assertEquals(currentUiState.drawCount, 1)
        TestCase.assertEquals(currentUiState.loseCount, 1)
    }

    @Test
    fun getPercentageTest() {
        val viewModel = StatisticsScreenViewModel()
        TestCase.assertEquals(viewModel.getPercentage("win"), 100.0f)
        TestCase.assertEquals(viewModel.getPercentage("draw"), 0.0f)
        TestCase.assertEquals(viewModel.getPercentage("lose"), 0.0f)

        viewModel.addEntry("TestFirstName", "TestSecondName", "0:1", "lose")
        TestCase.assertEquals(viewModel.getPercentage("win"), 50.0f)
        TestCase.assertEquals(viewModel.getPercentage("draw"), 0.0f)
        TestCase.assertEquals(viewModel.getPercentage("lose"), 50.0f)

        viewModel.addEntry("FirstName", "SecondName", "1:1", "draw")
        TestCase.assertEquals(viewModel.getPercentage("win"), 33.333334f)
        TestCase.assertEquals(viewModel.getPercentage("draw"), 33.333334f)
        TestCase.assertEquals(viewModel.getPercentage("lose"), 33.333334f)

        viewModel.addEntry("1Name", "2Name", "1:0", "win")
        TestCase.assertEquals(viewModel.getPercentage("win"), 50.0f)
        TestCase.assertEquals(viewModel.getPercentage("draw"), 25.0f)
        TestCase.assertEquals(viewModel.getPercentage("lose"), 25.0f)
    }
}