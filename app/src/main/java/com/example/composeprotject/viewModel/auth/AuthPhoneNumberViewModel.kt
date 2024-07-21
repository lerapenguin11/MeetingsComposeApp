package com.example.composeprotject.viewModel.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthPhoneNumberViewModel : ViewModel() {

    private val _activeAuthButton = MutableStateFlow(false)
    private val activeAuthButton: StateFlow<Boolean> = _activeAuthButton

    private val _validationPhoneNumber = MutableStateFlow<Boolean>(false)
    private val validationPhoneNumber: StateFlow<Boolean> = _validationPhoneNumber

    private val _phoneNumber = MutableStateFlow("")
    private val phoneNumber: StateFlow<String> = _phoneNumber

    fun getPhoneNumberFlow(): StateFlow<String> = phoneNumber

    fun getValidationPhoneNumberFlow(): StateFlow<Boolean> = validationPhoneNumber

    fun getActiveAuthButton(): StateFlow<Boolean> = activeAuthButton

    fun phoneNumber(phoneNumber: String) {
        _phoneNumber.update { phoneNumber }
    }

    fun activeAuthButton(isEnabled: Boolean) {
        _activeAuthButton.update { isEnabled }
    }

    fun validationPhoneNumber(isValidation: Boolean) {
        _validationPhoneNumber.update { isValidation }
    }
}