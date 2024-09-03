package com.example.domain.old.usecase.event

import com.example.common.utils_ui.MyEventVariant
import com.example.domain.old.model.Event
import com.example.domain.old.repository.EventRepository
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