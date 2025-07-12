package com.khaled.thmanyah.presentation.search

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
import com.khaled.thmanyah.presentation.components.EmptyStateView
import com.khaled.thmanyah.presentation.components.ErrorView
import com.khaled.thmanyah.presentation.components.LoadingIndicator
import com.khaled.thmanyah.presentation.components.QueueSection
import com.khaled.thmanyah.presentation.components.SearchBar
import com.khaled.thmanyah.presentation.components.SectionHeader
import com.khaled.thmanyah.presentation.components.SquareSection
import com.khaled.thmanyah.presentation.components.TwoLineGridSection


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.Black)) {
        SearchBar(
            query = viewModel.query.collectAsState().value.let { query ->
                if (query.isEmpty()) "" else query
            },
            onQueryChange = viewModel::onQueryChanged,
            onBack = { navController.popBackStack() }
        )

        when (val result = state.value) {
            is ApiResult.Loading -> LoadingIndicator()
            is ApiResult.Error -> ErrorView(result.exception)
            is ApiResult.Success<*> -> {
                val data = result.data as HomeViewState
                if (data.sections.isEmpty()) {
                    EmptyStateView()
                } else {
                    SearchResultContent(viewState = data)
                }
            }
        }
    }
}


@Composable
fun SearchResultContent(viewState: HomeViewState) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewState.sections) { section ->
            SectionHeader(section)
            when (section.type) {
                ShowContentType.SQUARE.value -> SquareSection(section.content)
                ShowContentType.BIG_SQUARE.value -> BigSquareSection(section.content)
                ShowContentType.TWO_LINES_GRID.value -> TwoLineGridSection(section.content)
                ShowContentType.QUEUE.value -> QueueSection(section.content)
                else -> SquareSection(section.content)
            }
        }
    }
}