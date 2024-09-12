package com.example.domain.usecase.combineUseCase

import com.example.domain.model.event.Meeting
import com.example.domain.model.eventDetails.MeetingDetails
import com.example.domain.usecase.getData.GetEventDetails
import com.example.domain.usecase.getData.GetEventsByCommunityId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullEventDetailsInfo : KoinComponent {
    private val getEventDetails: GetEventDetails by inject()
    private val getEventsByCommunityId: GetEventsByCommunityId by inject()

    private val eventDetailsFlow = combine(
        getEventDetails.execute(),
        getEventsByCommunityId.execute()
    ) { eventDetails, eventsByCommunityId ->
        CombineEventDetailsInfo(
            eventDetails = eventDetails,
            isLoadingFullData = false,
            eventsByCommunityId = eventsByCommunityId
        )
    }

    fun execute(): Flow<CombineEventDetailsInfo> = eventDetailsFlow
}

data class CombineEventDetailsInfo(
    val eventDetails: MeetingDetails?,
    val isLoadingFullData: Boolean,
    val eventsByCommunityId: List<Meeting>
)