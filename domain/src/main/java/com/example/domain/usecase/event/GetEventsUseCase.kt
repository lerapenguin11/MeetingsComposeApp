package com.example.domain.usecase.event

import com.example.domain.model.Event
import com.example.domain.model.EventVariant
import com.example.domain.repository.EventRepository

interface GetEventsUseCase {
    fun execute(variantEvent: String): List<Event>
}

internal class GetEventsUseCaseInteractor(private val repository: EventRepository) :
    GetEventsUseCase {

    override fun execute(variantEvent: String): List<Event> {
        return when (variantEvent) {
            EventVariant.ALL_EVENT.name -> repository.getAllEvent()
            EventVariant.ACTIVE_EVENT.name -> repository.getActiveEvent()
            else -> {
                emptyList<Event>()
            }
        }
    }
}