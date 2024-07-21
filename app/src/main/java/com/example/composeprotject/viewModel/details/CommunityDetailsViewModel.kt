package com.example.composeprotject.viewModel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CommunityDetails
import com.example.domain.usecase.details.GetCommunityByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityDetailsViewModel(private val getCommunityByIdUseCase: com.example.domain.usecase.details.GetCommunityByIdUseCase) :
    ViewModel() {

    private val _communityDetails = MutableStateFlow<com.example.domain.model.CommunityDetails?>(null)
    private val communityDetails: StateFlow<com.example.domain.model.CommunityDetails?> = _communityDetails

    fun getCommunityDetailsFlow() = communityDetails

    fun getCommunityById(communityId : Int) = viewModelScope.launch {
        _communityDetails.emit(value = getCommunityByIdUseCase(communityId = communityId))
    }
}