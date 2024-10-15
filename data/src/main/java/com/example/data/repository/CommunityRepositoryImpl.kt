package com.example.data.repository

import com.example.common.result.ResultData
import com.example.common.result.ResultStatus
import com.example.data.fakeData.generateCommunityDetails
import com.example.data.mappers.CommunityMapper
import com.example.domain.model.community.Community
import com.example.domain.model.communityDetails.CommunityDetails
import com.example.domain.repository.community.CommunityRepository
import com.example.network.api.CommunityApi
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommunityRepositoryImpl(
    private val mapper: CommunityMapper,
    private val service: CommunityApi
) : CommunityRepository {

    override fun getCommunities(userInterest: List<Int>?): Flow<List<Community>> {
        return flow {
            val response = service.getCommunity(categories = userInterest?.let {
                mapper.typeConvectorListIdToUriId(ids = it)
            })
            response.suspendOnSuccess {
                emit(value = data.map {
                    mapper.communitiesResponseToCommunities(
                        communitiesResponseItem = it
                    )
                }.take(MAX_ELEMENT))
            }.onFailure {
                message()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getCommunityDetails(communityId: Int): Flow<CommunityDetails> {
        return flow {
            emit(
                value = mapper.communityDetailsResponseToCommunityResponse(
                    generateCommunityDetails(
                        id = communityId
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun communitySubscription(
        communityId: Int,
        authToken: String
    ): Flow<ResultData<ResultStatus>> {
        return flow {
            val resultStatusCode = 200
            delay(2000)
            when (resultStatusCode) {
                200 -> emit(value = ResultData.Success(ResultStatus.SUCCESS))
            }
        }
    }
}

private const val MAX_ELEMENT = 6