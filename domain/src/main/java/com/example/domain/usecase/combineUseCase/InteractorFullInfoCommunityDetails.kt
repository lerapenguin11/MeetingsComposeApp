package com.example.domain.usecase.combineUseCase

import com.example.domain.model.communityDetails.CommunityDetails
import com.example.domain.model.event.Meeting
import com.example.domain.usecase.getData.GetCommunityDetails
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullInfoCommunityDetails : KoinComponent {
    private val getCommunityDetails: GetCommunityDetails by inject()

    private val communityDetailsFlow = combine(
        getCommunityDetails.execute(),
        getCommunityDetails.invoke()
    ) { communityDetails, meetings ->
        CombineCommunityDetails(
            communityDetails = communityDetails,
            eventsByCommunityId = meetings
        )
    }

    fun execute() = communityDetailsFlow
}

data class CombineCommunityDetails(
    val communityDetails: CommunityDetails?,
    val eventsByCommunityId: List<Meeting>
)