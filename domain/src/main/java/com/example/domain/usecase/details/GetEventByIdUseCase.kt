package com.example.domain.usecase.details

import com.example.domain.model.EventDetails
import com.example.domain.repository.EventDetailsRepository

interface GetEventByIdUseCase {
    fun execute(eventId: Int): EventDetails
}

internal class GetEventByIdInteractor(private val repository: EventDetailsRepository) :
    GetEventByIdUseCase {

    override fun execute(eventId: Int) = repository.getEventById(eventId = eventId)
}