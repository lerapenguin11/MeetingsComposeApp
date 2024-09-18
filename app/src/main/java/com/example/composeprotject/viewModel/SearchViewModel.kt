package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import com.example.composeprotject.screen.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow<String?>(null)
    private val searchQuery: StateFlow<String?> = _searchQuery

    private val _mainScreenState = MutableStateFlow(SearchState.MAIN_DEFAULT_SCREEN)
    private val mainScreenState: StateFlow<SearchState> = _mainScreenState

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