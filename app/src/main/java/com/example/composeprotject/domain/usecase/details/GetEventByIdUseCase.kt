package com.example.composeprotject.domain.usecase.details

import com.example.composeprotject.domain.repository.EventDetailsRepository

class GetEventByIdUseCase(private val repository: EventDetailsRepository) {

    operator fun invoke(eventId : Int) = repository.getEventById(eventId = eventId)
}