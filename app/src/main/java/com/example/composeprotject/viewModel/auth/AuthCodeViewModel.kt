package com.example.composeprotject.viewModel.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthCodeViewModel : ViewModel() {

    private val _code = MutableStateFlow("")
    private val code: StateFlow<String> = _code

    fun getCodeFlow() : StateFlow<String> = code

    fun setCode(code: String) {
        _code.value = code
    }
}