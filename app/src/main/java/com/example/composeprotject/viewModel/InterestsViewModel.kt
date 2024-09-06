package com.example.composeprotject.viewModel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.screen.state.InterestState
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.domain.model.interest.AddVariantInterests
import com.example.domain.model.interest.Interest
import com.example.domain.model.interest.UserInterestDomain
import com.example.domain.usecase.interest.AddUserInterestsUseCase
import com.example.domain.usecase.interest.GetInterestsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InterestsViewModel(
    private val getInterestsUseCase: GetInterestsUseCase,
    private val addUserInterestsUseCase: AddUserInterestsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<InterestsUiState>(InterestsUiState.Loading)
    private val uiState: StateFlow<InterestsUiState> = _uiState

    private val _buttonState = MutableStateFlow(FilledButtonState.ACTIVE_PRIMARY)
    private val buttonState: StateFlow<FilledButtonState> = _buttonState

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

    fun getButtonState() = buttonState

    fun toggleUserInterest(interest: Interest) {
        if (hasUserInterest(interest.id)) {
            updateUserInterests(interest)
        } else {
            deleteUserInterest(interest)
        }
    }

    fun addUserInterests(userInterests: List<Interest>, stateScreen: InterestState) =
        viewModelScope.launch {
            addUserInterestsUseCase.execute(
                userInterests = UserInterestDomain(
                    userInterest = userInterests,
                    addVariantInterests = determineOptionAddingInterests(stateScreen)
                ),
                onStart = { _buttonState.tryEmit(FilledButtonState.LOADING) },
                onComplete = { _buttonState.tryEmit(FilledButtonState.ACTIVE_SECONDARY) },
                onError = { /*TODO*/ }
            )
        }

    private fun determineOptionAddingInterests(stateScreen: InterestState): AddVariantInterests {
        return when (stateScreen) {
            InterestState.ONBOARDING -> AddVariantInterests.LOCAL
            InterestState.EDIT_INTERESTS -> AddVariantInterests.REMOTE_AND_LOCAL
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