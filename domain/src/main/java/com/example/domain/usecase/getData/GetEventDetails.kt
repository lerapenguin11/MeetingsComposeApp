package com.example.domain.usecase.getData

import com.example.domain.model.eventDetails.MeetingDetails
import com.example.domain.repository.event.EventRepository
import com.example.domain.usecase.details.GetEventDetailsInfoUseCase
import com.example.domain.usecase.details.InteractorLoadEventsByCommunityId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetEventDetails : KoinComponent {
    private val innerEvent: GetEventDetailsInfoUseCase by inject()
    private val repository: EventRepository by inject()

    private val loadEventsByCommunityId: InteractorLoadEventsByCommunityId by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventPrepared: Flow<MeetingDetails> =
        innerEvent.eventIdTrigger().filterNotNull().mapLatest { params ->
            val eventDetailsFlow = repository.getEventDetails(params = params)
            eventDetailsFlow.collectLatest { loadEventsByCommunityId.execute(communityId = it.organizers.id) }
            eventDetailsFlow
        }.flatMapMerge { it }

    fun execute(): Flow<MeetingDetails> = eventPrepared

}