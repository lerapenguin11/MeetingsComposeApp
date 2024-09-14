package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.ui.component.state.FilledButtonState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SingUpViewModel : ViewModel() {
    private val _userName: MutableStateFlow<String?> = MutableStateFlow(null)
    private val userName: StateFlow<String?> = _userName

    private val _phoneNumber: MutableStateFlow<String?> = MutableStateFlow(null)
    private val phoneNumber: StateFlow<String?> = _phoneNumber

    private val _confirmationCode: MutableStateFlow<String?> = MutableStateFlow(null)
    private val confirmationCode: StateFlow<String?> = _confirmationCode

    private val _buttonState: MutableStateFlow<FilledButtonState> =
        MutableStateFlow(FilledButtonState.DISABLED)
    private val buttonState: StateFlow<FilledButtonState> = _buttonState

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isLoading: StateFlow<Boolean> = _isLoading

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

    fun getButtonState() = buttonState

    fun getUserParam() = userParam

    fun getPhoneNumber() = phoneNumber

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

    suspend fun test() {
        delay(3000)
        _isLoading.update { true }
    }

    fun getIsLoading() = isLoading
}