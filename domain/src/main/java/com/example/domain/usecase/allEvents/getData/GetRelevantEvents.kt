package com.example.domain.usecase.allEvents.getData

import com.example.domain.repository.event.EventRepository
import com.example.domain.usecase.allEvents.GetRelevantEventsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRelevantEvents : KoinComponent {
    private val innerRelevantEvents: GetRelevantEventsUseCase by inject()
    private val repository: EventRepository by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventsPrepared: Flow<List<com.example.model.event.Meeting>> =
        innerRelevantEvents.trigger().flatMapLatest { queryParam ->
            flow {
                emit(value = queryParam?.let {
                    repository.getEventsByUserInterest(
                        eventType = com.example.model.event.EventListType.RELEVANT,
                        authToken = it.authToken,
                        userInterests = it.userInterests
                    )
                })
            }
        }.flatMapMerge { it ?: emptyFlow() }

    fun execute(): Flow<List<com.example.model.event.Meeting>> = eventsPrepared
}