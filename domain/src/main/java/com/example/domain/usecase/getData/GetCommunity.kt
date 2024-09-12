package com.example.domain.usecase.getData

import com.example.domain.model.community.Community
import com.example.domain.repository.community.CommunityRepository
import com.example.domain.usecase.event.GetMainInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCommunity : KoinComponent {
    private val innerEvents: GetMainInfoUseCase by inject()
    private val repository: CommunityRepository by inject()

    private val communityPrepared: Flow<List<Community>> =
        innerEvents.trigger().mapLatest { queryParam ->
            queryParam?.let {
                repository.getCommunities(
                    userInterest = it.userInterests
                )
            }
        }.flatMapMerge { it ?: flow { emptyList<Community>() } }

    fun execute(): Flow<List<Community>> = communityPrepared
}