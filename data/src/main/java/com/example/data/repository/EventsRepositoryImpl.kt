package com.example.data.repository

import com.example.data.fakeData.eventsFake
import com.example.data.mappers.EventsMapper
import com.example.data.responseModel.event.EventResponse
import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.repository.event.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EventsRepositoryImpl(
    private val mapper: EventsMapper
) : EventRepository {

    override fun getEvents(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?,
    ): Flow<List<Meeting>> {
        return flow {
            val filteredEvents = filterEvents(
                eventType = eventType,
                events = EventResponse()
            ).map { mapper.eventResponseToMeeting(it) }

            emit(
                value = filteredEvents.take(5)
            )
            //TODO: response = ...{...}.onFailure{ onError(message())
        }
            .flowOn(Dispatchers.IO)
    }

    private fun filterEvents(
        eventType: EventListType,
        events: EventResponse
    ): EventResponse {
        return when (eventType) {
            EventListType.RELEVANT -> {
                eventsFake() //TODO
            }

            EventListType.CLOSEST -> {
                eventsFake() //TODO
            }

            else -> {
                eventsFake()
            }
        }
    }
}