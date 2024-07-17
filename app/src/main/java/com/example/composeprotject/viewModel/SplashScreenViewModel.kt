package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    private val isLoading: StateFlow<Boolean> = _isLoading

    private val _isUserLoggedIn = MutableStateFlow(false)
    private val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    fun getIsLoadingFlow() : StateFlow<Boolean> = isLoading
    fun getIsUserLoggedIn() : StateFlow<Boolean> = isUserLoggedIn

    init {
        loadData()
        handleDeeplinks()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            _isLoading.value = false
        }
    }

    private fun handleDeeplinks() {
        _isUserLoggedIn.value = true
    }
}

private const val SPLASH_SCREEN_DURATION = 2000L
