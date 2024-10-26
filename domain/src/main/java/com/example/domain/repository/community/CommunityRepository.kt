package com.example.domain.repository.community

import com.example.common.result.ResultData
import com.example.common.result.ResultStatus
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    fun getCommunities(
        userInterest: List<Int>?
    ): Flow<List<com.example.model.community.Community>>

    fun getCommunityDetails(communityId: Int): Flow<com.example.model.communityDetails.CommunityDetails>

    fun communitySubscription(communityId: Int, authToken: String): Flow<ResultData<ResultStatus>>
}