package com.example.composeprotject.viewModel.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ShortInfoUser
import com.example.domain.usecase.user.GetShortInfoUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StillViewModel(private val getShortInfoUserUseCase: com.example.domain.usecase.user.GetShortInfoUserUseCase) : ViewModel() {

    private val _shortInfoUser = MutableStateFlow<com.example.domain.model.ShortInfoUser?>(null)
    private val shortInfoUser : StateFlow<com.example.domain.model.ShortInfoUser?> = _shortInfoUser

    fun shortInfoUserFlow() : StateFlow<com.example.domain.model.ShortInfoUser?> = shortInfoUser

    init {
        getShortInfoUser()
    }
    private fun getShortInfoUser() = viewModelScope.launch {
        _shortInfoUser.emit(value = getShortInfoUserUseCase())
    }
}