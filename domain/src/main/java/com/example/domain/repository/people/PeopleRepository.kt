package com.example.domain.repository.people

import com.example.domain.model.people.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPeopleByEventId(eventId: Int): Flow<List<People>>
    fun getPeopleByCommunityId(communityId: Int): Flow<List<People>>
}