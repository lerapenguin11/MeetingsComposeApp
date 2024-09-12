package com.example.data.repository

import com.example.data.fakeData.generatePeopleList
import com.example.data.mappers.PeopleMapper
import com.example.domain.model.people.People
import com.example.domain.repository.people.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PeopleRepositoryImpl(
    private val mapper: PeopleMapper
) : PeopleRepository {

    override fun getPeopleByEventId(eventId: Int): Flow<List<People>> {
        return flow {
            emit(value = generatePeopleList(count = 5).map { mapper.peopleResponseToPeople(it) })
        }.flowOn(Dispatchers.IO)
    }

    override fun getPeopleByCommunityId(communityId: Int): Flow<List<People>> {
        return flow {
            emit(value = generatePeopleList(count = 20).map { mapper.peopleResponseToPeople(it) })
        }.flowOn(Dispatchers.IO)
    }
}