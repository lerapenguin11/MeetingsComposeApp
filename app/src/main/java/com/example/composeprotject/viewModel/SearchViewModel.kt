package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.screen.state.SearchState
import com.example.domain.usecase.store.ReadeAuthTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel(private val readeAuthTokenUseCase: ReadeAuthTokenUseCase) : ViewModel() {
    private val _searchQuery = MutableStateFlow<String?>(null)
    private val searchQuery: StateFlow<String?> = _searchQuery

    private val _mainScreenState = MutableStateFlow(SearchState.MAIN_DEFAULT_SCREEN)
    private val mainScreenState: StateFlow<SearchState> = _mainScreenState

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _authToken = readeAuthTokenUseCase.execute().flatMapLatest { token ->
        flow {
            emit(value = token)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun getAuthToken() = _authToken
    fun getSearchQuery() = searchQuery
    fun getMainScreenState() = mainScreenState

    fun searchQueryUpdate(text: String?) {
        _searchQuery.update {
            text
        }
    }

    fun mainScreenStateUpdate(state: SearchState) {
        _mainScreenState.update {
            state
        }
    }
}