package com.example.composeprotject.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.nav.route.Graph
import com.example.domain.usecase.store.ReadOnBoardingInterestStateUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val readOnBoardingInterestState: ReadOnBoardingInterestStateUseCase
) :
    ViewModel() {

    private val _startDestination: MutableState<String> = mutableStateOf(Graph.ON_BOARDING)
    private val startDestination: MutableState<String> = _startDestination

    private val _isLoading = MutableStateFlow(true)
    private val isLoading = _isLoading.asStateFlow()

    init {
        getOnBoardingState()
    }

    fun getStartDestination() = startDestination
    fun getIsLoading() = isLoading

    private fun getOnBoardingState() {
        readOnBoardingInterestState.execute()
            .onEach { completed ->
                if (completed) {
                    _startDestination.value = Graph.MAIN

                } else {
                    _startDestination.value = Graph.ON_BOARDING
                }
                delay(500)
                _isLoading.update { false }
            }.launchIn(viewModelScope)
    }
}