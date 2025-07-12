package com.khaled.thmanyah.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.khaled.thmanyah.core.utils.ApiResult
import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.presentation.ShowContentType
import com.khaled.thmanyah.presentation.components.BigSquareSection
import com.khaled.thmanyah.presentation.components.ErrorView
import com.khaled.thmanyah.presentation.components.HeaderUI
import com.khaled.thmanyah.presentation.components.LoadingIndicator
import com.khaled.thmanyah.presentation.components.QueueSection
import com.khaled.thmanyah.presentation.components.SectionHeader
import com.khaled.thmanyah.presentation.components.SquareSection
import com.khaled.thmanyah.presentation.components.TwoLineGridSection


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black) // Set background to black here
    ) {
        when (val result = state.value) {
            is ApiResult.Loading -> LoadingIndicator()
            is ApiResult.Error -> ErrorView(result.exception)
            is ApiResult.Success<*> -> HomeContent(viewState = result.data as HomeViewState,navController)
        }
    }
}

@Composable
fun HomeContent(viewState: HomeViewState, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            HeaderUI(title = "Hello, Khaled Elsonbati.",
                onSearchClick = { navController.navigate("search") }
            )
        }
        items(viewState.sections) { section ->
            SectionHeader(section)
            when (section.type) {
                ShowContentType.SQUARE.value -> SquareSection(section.content)
                ShowContentType.BIG_SQUARE.value -> BigSquareSection(section.content)
                ShowContentType.TWO_LINES_GRID.value -> TwoLineGridSection(section.content)
                ShowContentType.QUEUE.value -> QueueSection(section.content)
                else -> SquareSection(section.content) // fallback style
            }
        }
    }
}