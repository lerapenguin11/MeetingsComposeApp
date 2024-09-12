package com.example.data.repository

import com.example.data.fakeData.communities
import com.example.data.mappers.CommunityMapper
import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import com.example.domain.repository.community.CommunityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommunityRepositoryImpl(
    private val mapper: CommunityMapper
) : CommunityRepository {

    override fun getCommunities(userInterest: List<Int>?): Flow<List<Community>> {
        return flow {
            val communities = communities().map {
                mapper.communitiesResponseToCommunities(communitiesResponseItem = it)
            }
            emit(value = communities.take(6))
        }.flowOn(Dispatchers.IO)
    }

    override fun getEventsByCommunityId(communityId: Int): Flow<List<Meeting>> {
        return flow {
            emit(value = emptyList<Meeting>())
        }.flowOn(Dispatchers.IO)
    }
}