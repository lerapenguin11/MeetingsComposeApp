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
import com.example.domain.usecase.signUp.test.SendUserParamAuthentication
import com.example.domain.usecase.store.token.SaveAuthTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingUpViewModel(
    private val getUserParamSendStatusCode: SendUserParamAuthentication,
    private val sendConfirmationCodeUseCase: SendConfirmationCodeUseCase,
    private val saveAuthTokenUseCase: SaveAuthTokenUseCase
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

    @OptIn(ExperimentalCoroutinesApi::class)
     private val isSendPhoneVerificationCodeFlow =
        getUserParamSendStatusCode.resultSendUserParam().flatMapLatest { result ->
             flow {
                 when (result) {
                     is PhoneNumberResult.Success<PhoneNumberStatus> -> {
                         val a = 1234
                         emit(value = false)
                     }

                     is PhoneNumberResult.Error -> {
                         emit(value = true)
                     }
                 }
             }
         }.stateIn(
             scope = viewModelScope,
             started = SharingStarted.WhileSubscribed(5_000),
             initialValue = true
        )

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

    fun getTokenFlow() = resultToken

    fun getIsSendPhoneVerificationCodeFlow() = isSendPhoneVerificationCodeFlow

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

    fun saveAuthToken(token: String) = viewModelScope.launch {
        saveAuthTokenUseCase.execute(token = token)
    }

    fun sendPhoneVerificationCode(userParam: UserParam) = viewModelScope.launch {
        getUserParamSendStatusCode.execute(userParam = userParam)
    }

    fun sendConfirmationCode(code: String, phoneNumber: String) = viewModelScope.launch {
        val response = sendConfirmationCodeUseCase.execute(code = code, phoneNumber = phoneNumber)
        response.collect { result ->
            when (result) {
                is PhoneNumberResult.Success<Token> -> {
                    _resultToken.update { result.data }
                }

                is PhoneNumberResult.Error -> {
                    _resultToken.update { Token(token = null, success = SendCodeStatus.ERROR) }
                }
            }
        }
    }
}