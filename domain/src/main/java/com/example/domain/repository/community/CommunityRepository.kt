package com.example.domain.repository.community

import com.example.common.result.ResultData
import com.example.common.result.ResultStatus
import com.example.domain.model.community.Community
import com.example.domain.model.communityDetails.CommunityDetails
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    fun getCommunities(
        userInterest: List<Int>?
    ): Flow<List<Community>>

    fun getCommunityDetails(communityId: Int): Flow<CommunityDetails>

    fun communitySubscription(communityId: Int, authToken: String): Flow<ResultData<ResultStatus>>
}