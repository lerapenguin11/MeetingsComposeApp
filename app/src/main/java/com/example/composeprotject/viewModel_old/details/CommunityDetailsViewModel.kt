package com.example.composeprotject.viewModel_old.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.old.model.CommunityDetails
import com.example.domain.old.usecase.details.GetCommunityByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CommunityDetailsViewModel(private val getCommunityByIdUseCase: GetCommunityByIdUseCase) :
    ViewModel() {

    private val _communityDetails =
        MutableStateFlow<CommunityDetails?>(null)
    private val communityDetails: StateFlow<CommunityDetails?> =
        _communityDetails

    fun getCommunityDetailsFlow() = communityDetails

    fun getCommunityById(communityId: Int) {
        getCommunityByIdUseCase.execute(communityId = communityId)
            .onEach { details ->
                _communityDetails.update { details }
            }
            .launchIn(viewModelScope)
    }
}