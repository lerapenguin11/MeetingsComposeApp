package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.domain.model.Profile
import com.example.composeprotject.domain.usecase.user.GetInfoUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getInfoUserProfileUseCase: GetInfoUserProfileUseCase
) : ViewModel() {

    private val _infoUserProfile = MutableStateFlow<Profile?>(null)
    private val infoUserProfile : StateFlow<Profile?> = _infoUserProfile

    fun getInfoUserProfileFlow() = infoUserProfile

    init {
        getInfoUserProfile()
    }

    private fun getInfoUserProfile() = viewModelScope.launch {
        _infoUserProfile.emit(value = getInfoUserProfileUseCase())
    }
}