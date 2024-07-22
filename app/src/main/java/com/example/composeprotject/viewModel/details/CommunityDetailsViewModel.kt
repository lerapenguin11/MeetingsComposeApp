package com.example.composeprotject.viewModel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.details.GetCommunityByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CommunityDetailsViewModel(private val getCommunityByIdUseCase: GetCommunityByIdUseCase) :
    ViewModel() {

    private val _communityDetails =
        MutableStateFlow<com.example.domain.model.CommunityDetails?>(null)
    private val communityDetails: StateFlow<com.example.domain.model.CommunityDetails?> =
        _communityDetails

    fun getCommunityDetailsFlow() = communityDetails

    fun getCommunityById(communityId: Int) {
        getCommunityByIdUseCase.execute(communityId = communityId)
            .onEach {
                _communityDetails.update { it }
            }
            .launchIn(viewModelScope)
    }
}