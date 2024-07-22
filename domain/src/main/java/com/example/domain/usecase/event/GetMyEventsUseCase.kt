package com.example.domain.usecase.event

import com.example.domain.model.Event
import com.example.domain.model.MyEventVariant
import com.example.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetMyEventsUseCase{
    fun execute(variantMyEvent: String) : Flow<List<Event>>
}

internal class GetMyEventsInteractor(private val repository: EventRepository) : GetMyEventsUseCase {

    override fun execute(variantMyEvent: String): Flow<List<Event>> {
        return when (variantMyEvent) {
            MyEventVariant.ACTIVE_EVENT.name -> flow { emit(repository.getMyActiveEvent()) }
            MyEventVariant.INACTIVE_EVENT.name -> flow { emit(repository.getMyInactiveEvent()) }
            else -> {
                flow { emit(emptyList<Event>()) }
            }
        }
    }
}