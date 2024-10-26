package com.example.domain.usecase.community

import com.example.domain.repository.community.CommunityRepository
import com.example.result.ResultData
import com.example.result.ResultStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

interface CommunitySubscriptionUseCase {
    fun execute(communityId: Int, authToken: String)
    fun resultCommunitySubscription(): Flow<ResultData<ResultStatus>>
}

internal class CommunitySubscriptionUseCaseImpl(private val repository: CommunityRepository) :
    CommunitySubscriptionUseCase {

    private val communitySubParamMutableStateFlow: MutableSharedFlow<com.example.model.community.CommunitySubscriptionParams?> =
        MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultCommunitySubscription =
        communitySubParamMutableStateFlow.mapLatest { communitySubscriptionParams ->
            communitySubscriptionParams?.let {
                repository.communitySubscription(
                    communityId = it.communityId,
                    authToken = it.authToken
                )
            }
        }

    init {
        resultCommunitySubscription.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun execute(communityId: Int, authToken: String) {
        communitySubParamMutableStateFlow.tryEmit(
            value = com.example.model.community.CommunitySubscriptionParams(
                communityId = communityId,
                authToken = authToken
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun resultCommunitySubscription(): Flow<ResultData<ResultStatus>> {
        return resultCommunitySubscription.flatMapMerge { it ?: emptyFlow() }
    }
}