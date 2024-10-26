package com.example.domain.usecase.allEvents

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetRelevantEventsUseCase {
    private val streamEventsWithQueryParam: MutableSharedFlow<com.example.model.allEvents.RelevantEventsQueryParams?> =
        MutableStateFlow(null)
    private var lastValue: com.example.model.allEvents.RelevantEventsQueryParams? = null

    fun loadRelevantEvents(
        queryParam: com.example.model.allEvents.RelevantEventsQueryParams
    ) {
        lastValue = queryParam
        streamEventsWithQueryParam.tryEmit(value = queryParam)
    }

    fun refresh() = lastValue?.run { streamEventsWithQueryParam.tryEmit(value = this) }

    fun trigger(): SharedFlow<com.example.model.allEvents.RelevantEventsQueryParams?> =
        streamEventsWithQueryParam
}

class InteractorLoadRelevantEvents : KoinComponent {
    private val innerRelevantEventsUseCase: GetRelevantEventsUseCase by inject()

    fun execute(queryParam: com.example.model.allEvents.RelevantEventsQueryParams) =
        innerRelevantEventsUseCase.loadRelevantEvents(queryParam = queryParam)
}

class InteractorRefreshRelevantEvents : KoinComponent {
    private val innerRelevantEventsUseCase: GetRelevantEventsUseCase by inject()

    fun execute() = innerRelevantEventsUseCase.refresh()
}