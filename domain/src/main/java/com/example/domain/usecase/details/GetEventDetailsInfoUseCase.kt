package com.example.domain.usecase.details

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetEventDetailsInfoUseCase {

    private val streamEventWithEventId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private var eventIdLastValue: Int? = null

    private val streamEventsWithCommunityId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private var communityIdLastValue: Int? = null


    fun loadEventDetails(eventId: Int) {
        eventIdLastValue = eventId
        streamEventWithEventId.tryEmit(eventId)
    }

    fun loadEventsByCommunityId(communityId: Int) {
        communityIdLastValue = communityId
        streamEventsWithCommunityId.tryEmit(communityId)
    }

    fun refresh() {
        eventIdLastValue?.run { streamEventWithEventId.tryEmit(this) }
        communityIdLastValue?.run { streamEventsWithCommunityId.tryEmit(this) }
    }

    fun eventIdTrigger(): SharedFlow<Int?> = streamEventWithEventId

    fun communityIdTrigger(): SharedFlow<Int?> = streamEventsWithCommunityId
}

class InteractorLoadEventDetailsInfo : KoinComponent {
    private val innerInfo: GetEventDetailsInfoUseCase by inject()

    fun execute(eventId: Int) {
        innerInfo.loadEventDetails(eventId = eventId)
    }
}

class InteractorLoadEventsByCommunityId : KoinComponent {
    private val innerEvents: GetEventDetailsInfoUseCase by inject()

    fun execute(communityId: Int) {
        innerEvents.loadEventsByCommunityId(communityId = communityId)
    }
}