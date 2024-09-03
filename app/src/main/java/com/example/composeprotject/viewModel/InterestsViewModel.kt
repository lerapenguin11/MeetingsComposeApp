package com.example.composeprotject.viewModel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.interest.GetInterestsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class InterestsViewModel(private val getInterestsUseCase: GetInterestsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<InterestsUiState>(InterestsUiState.Loading)
    private val uiState: StateFlow<InterestsUiState> = _uiState

    private val _interests = getInterestsUseCase.execute()
        .flatMapLatest { interests ->
            updateUIStateFlow(state = InterestsUiState.Idle)
            flow { emit(interests) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    private val interests: StateFlow<List<Interest>> = _interests

    fun getInterestsFlow() = interests

    fun getUIStateFlow() = uiState

    private fun updateUIStateFlow(state: InterestsUiState) {
        _uiState.update { state }
    }
}

@Stable
sealed interface InterestsUiState {
    data object Loading : InterestsUiState
    data object Idle : InterestsUiState
    data class Error(val message: String?) : InterestsUiState
}