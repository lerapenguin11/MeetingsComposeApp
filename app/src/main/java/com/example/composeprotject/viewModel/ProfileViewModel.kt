package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Profile
import com.example.domain.usecase.user.GetInfoUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getInfoUserProfileUseCase: GetInfoUserProfileUseCase
) : ViewModel() {

    private val _infoUserProfile = MutableStateFlow<Profile?>(null)
    private val infoUserProfile: StateFlow<Profile?> = _infoUserProfile

    fun getInfoUserProfileFlow() = infoUserProfile

    init {
        getInfoUserProfile()
    }

    private fun getInfoUserProfile() {
        _infoUserProfile.update { _ -> getInfoUserProfileUseCase.execute() }
    }
}