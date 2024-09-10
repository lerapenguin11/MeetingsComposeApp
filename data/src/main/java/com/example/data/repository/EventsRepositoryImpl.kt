package com.example.data.repository

import com.example.data.fakeData.eventsFake
import com.example.data.mappers.EventsMapper
import com.example.data.responseModel.event.EventResponse
import com.example.database.dao.UserInterestDao
import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.repository.event.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class EventsRepositoryImpl(
    private val mapper: EventsMapper,
    private val dao: UserInterestDao
) : EventRepository {

    private val _test = MutableStateFlow<List<Int>>(emptyList())
    private val test: StateFlow<List<Int>> = _test.asStateFlow()

    init {
        getUserInterest()
    }

    override fun getEventsByUserInterest(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ): Flow<List<Meeting>> {
        return flow {
            val filteredEvents = filterEvents(
                eventType = eventType,
                events = EventResponse()
            ).map { mapper.eventResponseToMeeting(it) }
            emit(
                value = filteredEvents.take(6)
            )
            //TODO: response = ...{...}.onFailure{ onError(message())
        }
            .flowOn(Dispatchers.IO)
    }

    override fun getEventsClosest(
        eventType: EventListType,
        userInterests: List<Int>?,
        city: String?,
        authToken: String?
    ): Flow<List<Meeting>> {
        return flow {
            val filteredEvents = filterEvents(
                eventType = eventType,
                events = EventResponse()
            ).map { mapper.eventResponseToMeeting(it) }
            emit(
                value = filteredEvents.filter { it.id % 2 == 0 }.take(6)
            )
            //TODO: response = ...{...}.onFailure{ onError(message())
        }
            .flowOn(Dispatchers.IO)
    }

    override fun getFilteredEventsByCategory(filterParam: List<Int>): Flow<List<Meeting>> {
        return flow {
            emit(value = eventsFake().map { mapper.eventResponseToMeeting(it) })
        }
    }

    private fun getUserInterest() {
        dao.getUserInterests().map {
            it.map { entity ->
                _test.emit(value = listOf(mapper.userInterestEntityToIdInterest(entity = entity)))
            }
        }
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