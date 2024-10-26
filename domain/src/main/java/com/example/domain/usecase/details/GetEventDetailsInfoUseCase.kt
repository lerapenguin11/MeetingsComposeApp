package com.example.domain.usecase.details

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetEventDetailsInfoUseCase {

    private val streamEventWithEventId: MutableStateFlow<com.example.model.eventDetails.EventDetailsParams?> =
        MutableStateFlow(null)
    private var eventIdLastValue: com.example.model.eventDetails.EventDetailsParams? = null

    private val streamEventsWithCommunityId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private var communityIdLastValue: Int? = null


    fun loadEventDetails(params: com.example.model.eventDetails.EventDetailsParams) {
        eventIdLastValue = params
        streamEventWithEventId.tryEmit(value = params)
    }

    fun loadEventsByCommunityId(communityId: Int) {
        communityIdLastValue = communityId
        streamEventsWithCommunityId.tryEmit(communityId)
    }

    fun refresh() {
        eventIdLastValue?.run { streamEventWithEventId.tryEmit(this) }
        communityIdLastValue?.run { streamEventsWithCommunityId.tryEmit(this) }
    }

    fun eventIdTrigger(): SharedFlow<com.example.model.eventDetails.EventDetailsParams?> =
        streamEventWithEventId

    fun communityIdTrigger(): SharedFlow<Int?> = streamEventsWithCommunityId
}

class InteractorLoadEventDetailsInfo : KoinComponent {
    private val innerInfo: GetEventDetailsInfoUseCase by inject()

    fun execute(params: com.example.model.eventDetails.EventDetailsParams) {
        innerInfo.loadEventDetails(params = params)
    }
}

class InteractorLoadEventsByCommunityId : KoinComponent {
    private val innerEvents: GetEventDetailsInfoUseCase by inject()

    fun execute(communityId: Int) {
        innerEvents.loadEventsByCommunityId(communityId = communityId)
    }
}