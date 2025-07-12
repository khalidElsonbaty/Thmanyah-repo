package com.khaled.thmanyah.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaled.thmanyah.core.utils.ApiResult
import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<HomeViewState>>(ApiResult.Loading)
    open val uiState: StateFlow<ApiResult<HomeViewState>> = _uiState

    init {
        getHomeSections()
    }

    private fun getHomeSections() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            try {
                val homeViewState = getHomeSectionsUseCase()
                _uiState.value = ApiResult.Success(homeViewState)
            } catch (e: Exception) {
                _uiState.value = ApiResult.Error(e)
            }
        }
    }
}
