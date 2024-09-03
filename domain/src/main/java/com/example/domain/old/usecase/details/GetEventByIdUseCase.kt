package com.example.domain.old.usecase.details

import com.example.domain.old.model.EventDetails
import com.example.domain.old.repository.EventDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetEventByIdUseCase {
    fun execute(eventId: Int): Flow<EventDetails>
}

internal class GetEventByIdInteractor(private val repository: EventDetailsRepository) :
    GetEventByIdUseCase {

    override fun execute(eventId: Int) = flow { emit(repository.getEventById(eventId = eventId)) }
}