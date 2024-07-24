package com.example.domain.usecase.details

import com.example.domain.model.EventDetails
import com.example.domain.repository.EventDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetEventByIdUseCase {
    fun execute(eventId: Int): Flow<EventDetails>
}

internal class GetEventByIdInteractor(private val repository: EventDetailsRepository) :
    GetEventByIdUseCase {

    override fun execute(eventId: Int) = flow { emit(repository.getEventById(eventId = eventId)) }
}