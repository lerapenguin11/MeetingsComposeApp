package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(

) : ViewModel() {

    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code

    private val _activeAuthButton = MutableStateFlow(false)
    val activeAuthButton : StateFlow<Boolean> = _activeAuthButton

    fun setCode(code: String) {
        _code.value = code
    }

    fun activeAuthButton(isEnabled : Boolean){
        _activeAuthButton.value = isEnabled
    }
}