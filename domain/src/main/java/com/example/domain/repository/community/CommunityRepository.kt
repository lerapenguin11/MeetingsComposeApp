package com.example.domain.repository.community

import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    fun getCommunities(
        userInterest: List<Int>?
    ): Flow<List<Community>>

    fun getEventsByCommunityId(communityId: Int): Flow<List<Meeting>>
}