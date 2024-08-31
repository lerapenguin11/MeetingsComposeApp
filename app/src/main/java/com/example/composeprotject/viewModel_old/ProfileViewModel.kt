package com.example.composeprotject.viewModel_old

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Profile
import com.example.domain.usecase.user.GetInfoUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

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
        getInfoUserProfileUseCase.execute()
            .onEach { userProfile ->
                _infoUserProfile.update { userProfile }
            }
            .launchIn(viewModelScope)
    }
}