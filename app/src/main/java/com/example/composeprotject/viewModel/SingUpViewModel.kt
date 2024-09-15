package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.common.result.SendCodeStatus
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.domain.model.signUp.Token
import com.example.domain.model.signUp.UserParam
import com.example.domain.usecase.signUp.SendConfirmationCodeUseCase
import com.example.domain.usecase.signUp.SendPhoneVerificationCodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SingUpViewModel(
    private val sendPhoneVerificationCodeUseCase: SendPhoneVerificationCodeUseCase,
    private val sendConfirmationCodeUseCase: SendConfirmationCodeUseCase
) : ViewModel() {
    private val _userName: MutableStateFlow<String?> = MutableStateFlow(null)
    private val userName: StateFlow<String?> = _userName

    private val _phoneNumber: MutableStateFlow<String?> = MutableStateFlow(null)
    private val phoneNumber: StateFlow<String?> = _phoneNumber

    private val _confirmationCode: MutableStateFlow<String?> = MutableStateFlow(null)
    private val confirmationCode: StateFlow<String?> = _confirmationCode

    private val _buttonState: MutableStateFlow<FilledButtonState> =
        MutableStateFlow(FilledButtonState.DISABLED)
    private val buttonState: StateFlow<FilledButtonState> = _buttonState

    private val _isSendPhoneVerificationCodeFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val isSendPhoneVerificationCodeFlow: StateFlow<Boolean> =
        _isSendPhoneVerificationCodeFlow

    private val _resultToken = MutableStateFlow<Token?>(null)
    private val resultToken: StateFlow<Token?> = _resultToken

    private val userParam = combine(
        userName,
        phoneNumber
    ) { t1, t2 ->
        Pair(t1, t2)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Pair(null, null)
    )

    fun getButtonStateFlow() = buttonState

    fun getUserParamFlow() = userParam

    fun getPhoneNumberFlow() = phoneNumber

    fun getCodeFlow() = confirmationCode

    fun updateButtonState(state: FilledButtonState) {
        _buttonState.update { state }
    }

    fun updateUserName(name: String) {
        _userName.update { name }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.update { phoneNumber }
    }

    fun updateConfirmationCode(code: String) {
        _confirmationCode.update { code }
    }

    fun sendPhoneVerificationCode(userParam: UserParam) {
        val response = sendPhoneVerificationCodeUseCase.execute(param = userParam)
        response.onEach {
            when (it) {
                is PhoneNumberResult.Success<PhoneNumberStatus> -> {
                }

                is PhoneNumberResult.Error -> {}
            }
            _isSendPhoneVerificationCodeFlow.update { false }
        }.launchIn(scope = viewModelScope)
    }

    fun sendConfirmationCode(code: String) {
        val response = sendConfirmationCodeUseCase.execute(code = code)
        response.onEach { result ->
            when (result) {
                is PhoneNumberResult.Success<Token> -> {
                    _resultToken.update { result.data }
                }

                is PhoneNumberResult.Error -> {
                    _resultToken.update { Token(token = null, success = SendCodeStatus.ERROR) }
                }
            }
        }.launchIn(scope = viewModelScope)
    }

    fun getIsLoading() = isSendPhoneVerificationCodeFlow
}