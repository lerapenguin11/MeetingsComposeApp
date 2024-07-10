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

    private val _code = MutableStateFlow("")
    val code : StateFlow<String> = _code

    init {
        loadData()
        handleDeeplinks()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(1500)
            _isLoading.value = false
        }
    }

    private fun handleDeeplinks() {
        _isUserLoggedIn.value = true
    }

    fun setCode(code : String){
        _code.value = code
    }
}
