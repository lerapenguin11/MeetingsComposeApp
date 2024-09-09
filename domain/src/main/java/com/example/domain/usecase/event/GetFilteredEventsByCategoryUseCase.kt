package com.example.domain.usecase.event

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetFilteredEventsByCategoryUseCase {
    private val streamEventsWithQueryParam: MutableStateFlow<List<Int>?> =
        MutableStateFlow(null)
    private var lastValue: List<Int>? = null

    fun loadMainInfo(
        categories: List<Int>
    ) {
        lastValue = categories
        streamEventsWithQueryParam.tryEmit(value = categories)
    }

    fun refresh() {
        lastValue?.run { streamEventsWithQueryParam.tryEmit(this) }
    }

    fun trigger(): SharedFlow<List<Int>?> =
        streamEventsWithQueryParam
}

class InteractorLoadFilteredEventsByCategory : KoinComponent {
    private val innerFilteredEvents: GetFilteredEventsByCategoryUseCase by inject()

    fun execute(categories: List<Int>) = innerFilteredEvents.loadMainInfo(
        categories = categories
    )
}