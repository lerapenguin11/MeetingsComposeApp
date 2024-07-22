package com.example.domain.usecase.event

import com.example.domain.model.Event
import com.example.domain.model.EventVariant
import com.example.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetEventsUseCase {
    fun execute(variantEvent: String): Flow<List<Event>>
}

internal class GetEventsUseCaseInteractor(private val repository: EventRepository) :
    GetEventsUseCase {

    override fun execute(variantEvent: String): Flow<List<Event>> {
        return when (variantEvent) {
            EventVariant.ALL_EVENT.name -> flow { emit(repository.getAllEvent()) }
            EventVariant.ACTIVE_EVENT.name -> flow { emit(repository.getActiveEvent()) }
            else -> {
                flow { emit(emptyList<Event>()) }
            }
        }
    }
}