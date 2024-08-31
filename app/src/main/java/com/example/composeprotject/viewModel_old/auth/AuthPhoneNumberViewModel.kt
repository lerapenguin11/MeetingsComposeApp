package com.example.composeprotject.viewModel_old.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.verfication.PostPhoneNumberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthPhoneNumberViewModel(private val postPhoneNumberUseCase: PostPhoneNumberUseCase) :
    ViewModel() {

    private val _activeAuthButton = MutableStateFlow(false)
    private val activeAuthButton: StateFlow<Boolean> = _activeAuthButton

    private val _validationPhoneNumber = MutableStateFlow(false)
    private val validationPhoneNumber: StateFlow<Boolean> = _validationPhoneNumber

    private val _phoneNumber = MutableStateFlow("")
    private val phoneNumber: StateFlow<String> = _phoneNumber

    private val _isLoadingSendPhoneNumber = MutableStateFlow(false)
    private val isLoadingSendPhoneNumber: StateFlow<Boolean> = _isLoadingSendPhoneNumber

    fun getPhoneNumberFlow(): StateFlow<String> = phoneNumber

    fun getValidationPhoneNumberFlow(): StateFlow<Boolean> = validationPhoneNumber

    fun getActiveAuthButton(): StateFlow<Boolean> = activeAuthButton

    fun isLoadingSendPhoneNumberFlow(): StateFlow<Boolean> = isLoadingSendPhoneNumber

    fun phoneNumber(phoneNumber: String) {
        _phoneNumber.update { phoneNumber }
    }

    fun activeAuthButton(isEnabled: Boolean) {
        _activeAuthButton.update { isEnabled }
    }

    fun validationPhoneNumber(isValidation: Boolean) {
        _validationPhoneNumber.update { isValidation }
    }

    fun sendPhoneNumberReceiveCode(phoneNumber: String) = viewModelScope.launch {
        postPhoneNumberUseCase.execute(phoneNumber = phoneNumber)
        _isLoadingSendPhoneNumber.update { true }
    }
}