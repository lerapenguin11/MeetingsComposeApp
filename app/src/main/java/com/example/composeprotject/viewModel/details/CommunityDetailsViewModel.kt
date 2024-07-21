package com.example.composeprotject.viewModel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.domain.model.CommunityDetails
import com.example.composeprotject.domain.usecase.details.GetCommunityByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityDetailsViewModel(private val getCommunityByIdUseCase: GetCommunityByIdUseCase) :
    ViewModel() {

    private val _communityDetails = MutableStateFlow<CommunityDetails?>(null)
    private val communityDetails: StateFlow<CommunityDetails?> = _communityDetails

    fun getCommunityDetailsFlow() = communityDetails

    fun getCommunityById(communityId : Int) = viewModelScope.launch {
        _communityDetails.emit(value = getCommunityByIdUseCase(communityId = communityId))
    }
}