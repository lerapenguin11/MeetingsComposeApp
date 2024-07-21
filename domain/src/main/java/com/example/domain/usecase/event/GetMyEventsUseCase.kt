package com.example.domain.usecase.event

import com.example.domain.model.Event
import com.example.domain.model.MyEventVariant
import com.example.domain.repository.EventRepository

interface GetMyEventsUseCase{
    fun execute(variantMyEvent: String) : List<Event>
}

internal class GetMyEventsInteractor(private val repository: EventRepository) : GetMyEventsUseCase {

    override fun execute(variantMyEvent: String): List<Event> {
        return when (variantMyEvent) {
            MyEventVariant.ACTIVE_EVENT.name -> repository.getMyActiveEvent()
            MyEventVariant.INACTIVE_EVENT.name -> repository.getMyInactiveEvent()
            else -> {
                emptyList<Event>()
            }
        }
    }
}