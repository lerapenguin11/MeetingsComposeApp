package com.example.composeprotject.domain.usecase.event

import com.example.composeprotject.common.MyEventVariant
import com.example.composeprotject.domain.model.nav.Event
import com.example.composeprotject.domain.repository.EventRepository

class GetMyEventsUseCase(private val repository: EventRepository) {

    fun getMyEvents(variantMyEvent: String): List<Event> {
        return when (variantMyEvent) {
            MyEventVariant.ACTIVE_EVENT.name -> repository.getMyActiveEvent()
            MyEventVariant.INACTIVE_EVENT.name -> repository.getMyInactiveEvent()
            else -> {
                emptyList<Event>()
            }
        }
    }
}