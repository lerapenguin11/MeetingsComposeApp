package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.nav.route.Graph
import com.example.domain.usecase.store.settings.ReadOnBoardingInterestStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationOnboardingViewModel(private val readOnBoardingInterestState: ReadOnBoardingInterestStateUseCase) :
    ViewModel() {

    private val _startDestination: MutableStateFlow<String> = MutableStateFlow(Graph.ON_BOARDING)
    private val startDestination: StateFlow<String> = _startDestination

    init {
        getOnBoardingState()
    }

    fun getStartDestination() = startDestination

    private fun getOnBoardingState() {
        readOnBoardingInterestState.execute()
            .onEach { completed ->
                if (completed) {
                    _startDestination.value = Graph.MAIN

                } else {
                    _startDestination.value = Graph.ON_BOARDING
                }
            }.launchIn(viewModelScope)
    }
}