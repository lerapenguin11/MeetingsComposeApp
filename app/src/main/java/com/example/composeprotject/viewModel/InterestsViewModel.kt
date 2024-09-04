package com.example.composeprotject.viewModel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.interest.GetInterestsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class InterestsViewModel(private val getInterestsUseCase: GetInterestsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<InterestsUiState>(InterestsUiState.Loading)
    private val uiState: StateFlow<InterestsUiState> = _uiState

    private val _allInterests = getInterestsUseCase.execute()
        .flatMapLatest { interests ->
            updateUIStateFlow(state = InterestsUiState.Idle)
            flow { emit(interests) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    private val allInterests: StateFlow<List<Interest>> = _allInterests

    private val _userInterests = MutableStateFlow<List<Interest>>(emptyList())
    private val userInterests: StateFlow<List<Interest>> = _userInterests

    private val combinedInterests = combine(
        getInterestsFlow(),
        getUserInterests()
    ) { allInterests, userInterests ->
        Pair(allInterests, userInterests)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Pair(emptyList(), emptyList())
    )

    fun getCombinedInterests() = combinedInterests

    fun getUIStateFlow() = uiState

    fun toggleUserInterest(interest: Interest) {
        if (hasUserInterest(interest.id)) {
            updateUserInterests(interest)
        } else {
            deleteUserInterest(interest)
        }
    }

    private fun getInterestsFlow() = allInterests

    private fun getUserInterests() = userInterests

    private fun hasUserInterest(id: Int): Boolean {
        return _userInterests.value.none { it.id == id }
    }

    private fun updateUserInterests(interest: Interest) {
        _userInterests.update { currentInterests ->
            currentInterests + interest
        }
    }

    private fun deleteUserInterest(interest: Interest) {
        _userInterests.update { currentInterests ->
            currentInterests.filterNot { it.id == interest.id }
        }
    }

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