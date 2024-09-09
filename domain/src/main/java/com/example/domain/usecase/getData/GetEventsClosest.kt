package com.example.domain.usecase.getData

import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.repository.event.EventRepository
import com.example.domain.usecase.event.GetMainInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetEventsClosest : KoinComponent {
    private val repository: EventRepository by inject()
    private val innerEvents: GetMainInfoUseCase by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventsPrepared: Flow<List<Meeting>> =
        innerEvents.trigger().mapLatest { queryParam ->
            queryParam?.let {
                repository.getEventsClosest(
                    eventType = EventListType.CLOSEST,
                    authToken = it.authToken,
                    userInterests = it.userInterests,
                    city = it.city
                )
            }
        }.flatMapMerge { it ?: flow { emptyList<Meeting>() } }

    fun execute(): Flow<List<Meeting>> = eventsPrepared
}