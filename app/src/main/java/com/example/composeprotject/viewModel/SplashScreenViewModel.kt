package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        loadData()
        handleDeeplinks()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }
    }

    private fun handleDeeplinks() {
        _isUserLoggedIn.value = true
    }
}
