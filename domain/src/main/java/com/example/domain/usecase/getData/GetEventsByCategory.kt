package com.example.domain.usecase.getData

import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.repository.event.EventRepository
import com.example.domain.usecase.main.GetMainInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetEventsByCategory : KoinComponent {
    private val repository: EventRepository by inject()
    private val innerEvents: GetMainInfoUseCase by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventsPrepared: Flow<List<Meeting>> =
        innerEvents.trigger().flatMapLatest { queryParam ->
            flow {
                emit(value = queryParam?.let {
                    repository.getEventsByUserInterest(
                        eventType = EventListType.RELEVANT,
                        authToken = it.authToken,
                        userInterests = it.userInterests
                    )
                })
            }
        }.flatMapMerge { it ?: emptyFlow() }

    fun execute(): Flow<List<Meeting>> = eventsPrepared
}