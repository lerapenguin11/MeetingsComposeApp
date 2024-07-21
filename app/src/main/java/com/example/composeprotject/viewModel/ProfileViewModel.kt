package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Profile
import com.example.domain.usecase.user.GetInfoUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getInfoUserProfileUseCase: com.example.domain.usecase.user.GetInfoUserProfileUseCase
) : ViewModel() {

    private val _infoUserProfile = MutableStateFlow<com.example.domain.model.Profile?>(null)
    private val infoUserProfile : StateFlow<com.example.domain.model.Profile?> = _infoUserProfile

    fun getInfoUserProfileFlow() = infoUserProfile

    init {
        getInfoUserProfile()
    }

    private fun getInfoUserProfile() = viewModelScope.launch {
        _infoUserProfile.emit(value = getInfoUserProfileUseCase())
    }
}