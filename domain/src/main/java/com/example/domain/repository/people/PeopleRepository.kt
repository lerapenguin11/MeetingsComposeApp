package com.example.domain.repository.people

import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPeopleByEventId(eventId: Int): Flow<List<com.example.model.people.People>>
    fun getPeopleByCommunityId(communityId: Int): Flow<List<com.example.model.people.People>>
}