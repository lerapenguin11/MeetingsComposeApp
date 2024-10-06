package com.example.data.repository

import androidx.annotation.WorkerThread
import com.example.data.fakeData.eventDetailsFake
import com.example.data.mappers.EventsMapper
import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.model.eventDetails.MeetingDetails
import com.example.domain.repository.event.EventRepository
import com.example.network.api.EventApi
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EventsRepositoryImpl(
    private val mapper: EventsMapper,
    private val service: EventApi
) : EventRepository {

    @WorkerThread
    override fun getEventsByUserInterest(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ): Flow<List<Meeting>> {
        return flow {
            val response = service.getMeetings(
                eventType = eventType.value,
                categories = userInterests?.let {
                    mapper.typeConvectorListIdToUriId(ids = it)
                }
            )
            response.suspendOnSuccess {
                emit(value = data.map { mapper.eventResponseToMeeting(it) }.take(MAX_ELEMENT))
            }.onFailure {
                message()
            }
        }
            .flowOn(Dispatchers.IO)
    }

    override fun getEventsByCommunityId(communityId: Int): Flow<List<Meeting>> {
        return flow {
            emit(value = emptyList<Meeting>())
        }.flowOn(Dispatchers.IO)
    }

    override fun getEventsClosest(
        eventType: EventListType,
        userInterests: List<Int>?,
        city: String?,
        authToken: String?
    ): Flow<List<Meeting>> {
        return flow {
            val response = service.getMeetings(
                eventType = eventType.value,
                categories = userInterests?.let {
                    mapper.typeConvectorListIdToUriId(ids = it)
                },
                city = city
            )
            response.suspendOnSuccess {
                emit(
                    value = data.map { mapper.eventResponseToMeeting(it) }.take(MAX_ELEMENT)
                )
            }.onFailure {
                message()
            }
        }
            .flowOn(Dispatchers.IO)
    }

    override fun getFilteredEventsByCategory(filterParam: List<Int>): Flow<List<Meeting>> {
        return flow {
            val response = service.getMeetings(
                categories = if (filterParam.isEmpty()) null else filterParam.let {
                    mapper.typeConvectorListIdToUriId(ids = it)
                }
            )
            response.suspendOnSuccess {
                emit(value = data.map { mapper.eventResponseToMeeting(it) })
            }.onFailure {
                message()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getEventDetails(eventId: Int): Flow<MeetingDetails> {
        return flow {
            emit(
                value = mapper.eventDetailsResponseToEventDetails(
                    item = eventDetailsFake().elementAt(
                        eventId
                    )
                )
            )
        }.flowOn(context = Dispatchers.IO)
    }
}

private const val MAX_ELEMENT = 6