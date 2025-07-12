package com.khaled.thmanyah.presentation.home

import com.khaled.thmanyah.core.utils.ApiResult
import com.khaled.thmanyah.domain.model.HomeViewState
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeHomeViewModel(
    testState: ApiResult<HomeViewState>
) : HomeViewModel(mockk(relaxed = true)) {

    private val _uiState = MutableStateFlow(testState)
    override val uiState: StateFlow<ApiResult<HomeViewState>> = _uiState
}