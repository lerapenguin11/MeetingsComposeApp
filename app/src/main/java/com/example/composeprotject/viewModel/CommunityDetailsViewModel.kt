package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.combineUseCase.CombineCommunityDetails
import com.example.domain.usecase.combineUseCase.InteractorFullInfoCommunityDetails
import com.example.domain.usecase.details.InteractorLoadCommunityDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommunityDetailsViewModel(
    private val loadCommunityDetails: InteractorLoadCommunityDetails,
    private val interactorFullInfoCommunityDetails: InteractorFullInfoCommunityDetails
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val communityDetails: StateFlow<CombineCommunityDetails> =
        interactorFullInfoCommunityDetails.execute().filterNotNull().flatMapLatest {
            flow {
                emit(value = it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CombineCommunityDetails(
                communityDetails = null,
                eventsByCommunityId = emptyList()
            )
        )

    fun getCommunityDetails() = communityDetails

    fun loadCommunityDetails(communityId: Int) = viewModelScope.launch {
        loadCommunityDetails.execute(communityId = communityId)
    }
}