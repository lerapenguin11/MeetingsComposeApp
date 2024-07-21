package com.example.domain.usecase.details

import com.example.domain.repository.EventDetailsRepository

class GetEventByIdUseCase(private val repository: EventDetailsRepository) {

    operator fun invoke(eventId : Int) = repository.getEventById(eventId = eventId)
}