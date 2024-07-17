package com.example.composeprotject.viewModel.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthPhoneNumberViewModel : ViewModel() {

    private val _activeAuthButton = MutableStateFlow(false)
    val activeAuthButton : StateFlow<Boolean> = _activeAuthButton

    private val _validationPhoneNumber = MutableStateFlow<Boolean>(false)
    private val validationPhoneNumber : StateFlow<Boolean> = _validationPhoneNumber

    private val _phoneNumber = MutableStateFlow("")
    private val phoneNumber : StateFlow<String> = _phoneNumber

    fun getPhoneNumberFlow() : StateFlow<String> = phoneNumber

    fun getValidationPhoneNumberFlow() : StateFlow<Boolean> = validationPhoneNumber

    fun phoneNumber(phoneNumber : String){
        _phoneNumber.value = phoneNumber
    }

    fun activeAuthButton(isEnabled : Boolean){
        _activeAuthButton.value = isEnabled
    }

    fun validationPhoneNumber(isValidation : Boolean){
        _validationPhoneNumber.value = isValidation
    }
}