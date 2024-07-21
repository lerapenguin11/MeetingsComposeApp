package com.example.domain.usecase.event

import com.example.composeprotject.common.MyEventVariant
import com.example.domain.model.Event
import com.example.domain.repository.EventRepository

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