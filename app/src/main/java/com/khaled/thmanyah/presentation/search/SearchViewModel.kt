package com.khaled.thmanyah.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaled.thmanyah.core.utils.ApiResult
import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.domain.usecase.GetSearchSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: GetSearchSectionsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _uiState = MutableStateFlow<ApiResult<HomeViewState>>(ApiResult.Success(HomeViewState(emptyList())))
    val uiState: StateFlow<ApiResult<HomeViewState>> = _uiState

    init {
        viewModelScope.launch {
            _query.debounce(200L.milliseconds)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        _uiState.value = ApiResult.Success(HomeViewState(emptyList()))
                    } else {
                        try {
                            getSearchResults(query)
                        } catch (e: Exception) {
                            _uiState.value = ApiResult.Error(e)
                        }
                    }
                }
        }
    }

   suspend fun getSearchResults(newQuery: String) {
            _uiState.value = ApiResult.Loading
            try {
                val searchViewState = searchUseCase(newQuery)
                _uiState.value = ApiResult.Success(searchViewState)
            } catch (e: Exception) {
                _uiState.value = ApiResult.Error(e)
            }

    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }
}
