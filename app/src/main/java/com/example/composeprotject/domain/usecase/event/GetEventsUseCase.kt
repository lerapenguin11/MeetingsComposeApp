package com.example.composeprotject.domain.usecase.event

import com.example.composeprotject.common.EventVariant
import com.example.composeprotject.domain.model.nav.Event
import com.example.composeprotject.domain.repository.EventRepository

class GetEventsUseCase(private val repository: EventRepository) {

    fun getEvents(variantEvent: String): List<Event> {
        return when (variantEvent) {
            EventVariant.ALL_EVENT.name -> repository.getAllEvent()
            EventVariant.ACTIVE_EVENT.name -> repository.getActiveEvent()
            else -> {
                emptyList<Event>()
            }
        }
    }
}