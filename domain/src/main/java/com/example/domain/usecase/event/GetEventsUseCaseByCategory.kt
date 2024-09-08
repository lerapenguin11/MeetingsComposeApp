package com.example.domain.usecase.event

import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.repository.event.EventRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetEventsUseCaseByCategory {

    private val streamEventsWithEventQueryParam: MutableStateFlow<Triple<EventListType, List<Int>?, String?>?> =
        MutableStateFlow(null)
    private var lastValue: Triple<EventListType, List<Int>?, String?>? = null

    fun loadEvents(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ) {
        lastValue = Triple(eventType, userInterests, authToken)
        streamEventsWithEventQueryParam.tryEmit(value = Triple(eventType, userInterests, authToken))
    }

    fun refresh() {
        lastValue?.run { streamEventsWithEventQueryParam.tryEmit(this) }
    }

    fun trigger(): SharedFlow<Triple<EventListType, List<Int>?, String?>?> =
        streamEventsWithEventQueryParam

}

class InteractorLoadEventsByCategory : KoinComponent {
    private val innerEvents: GetEventsUseCaseByCategory by inject()
    fun execute(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ) {
        innerEvents.loadEvents(
            eventType = eventType,
            userInterests = userInterests,
            authToken = authToken
        )
    }
}

class InteractorRefreshEvents : KoinComponent {
    private val innerEvents: GetEventsUseCaseByCategory by inject()
    fun execute() {
        innerEvents.refresh()
    }
}

class GetEventsByCategory : KoinComponent {
    private val repository: EventRepository by inject()
    private val innerEvents: GetEventsUseCaseByCategory by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventsPrepared: Flow<List<Meeting>> = innerEvents.trigger().mapLatest { it ->
        it?.let {
            repository.getEvents(
                eventType = it.first,
                authToken = it.third,
                userInterests = it.second
            )
        }
    }.flatMapMerge { it ?: flow { emptyList<Meeting>() } }

    fun execute(): Flow<List<Meeting>> = eventsPrepared
}