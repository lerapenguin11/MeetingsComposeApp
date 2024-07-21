package com.example.composeprotject.viewModel.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ShortInfoUser
import com.example.domain.usecase.user.GetShortInfoUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StillViewModel(private val getShortInfoUserUseCase: GetShortInfoUserUseCase) : ViewModel() {

    private val _shortInfoUser = MutableStateFlow<ShortInfoUser?>(null)
    private val shortInfoUser : StateFlow<ShortInfoUser?> = _shortInfoUser

    fun shortInfoUserFlow() : StateFlow<ShortInfoUser?> = shortInfoUser

    init {
        getShortInfoUser()
    }
    private fun getShortInfoUser() = viewModelScope.launch {
        _shortInfoUser.update { _ ->
            getShortInfoUserUseCase.execute()
        }
    }
}