package com.example.domain.usecase.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetFilteredEventsUseCase {
    private val streamEventsWithQueryParam: MutableStateFlow<List<Int>?> =
        MutableStateFlow(null)
    private var lastValue: List<Int>? = null

    fun loadFiltered(
        filterParam: List<Int>
    ) {
        lastValue = filterParam
        streamEventsWithQueryParam.tryEmit(value = filterParam)
    }

    fun trigger(): StateFlow<List<Int>?> = streamEventsWithQueryParam

    fun refresh() {
        lastValue?.run { streamEventsWithQueryParam.tryEmit(value = this) }
    }
}

class InteractorLoadFilteredEvents : KoinComponent {
    private val innerFilteredEvents: GetFilteredEventsUseCase by inject()

    fun execute(filterParam: List<Int>) =
        innerFilteredEvents.loadFiltered(filterParam = filterParam)
}

class InteractorRefreshFilteredEvents : KoinComponent {
    private val innerFilteredEvents: GetFilteredEventsUseCase by inject()

    fun execute() = innerFilteredEvents.refresh()
}