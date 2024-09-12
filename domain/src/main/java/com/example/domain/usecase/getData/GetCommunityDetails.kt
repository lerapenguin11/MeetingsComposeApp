package com.example.domain.usecase.getData

import com.example.domain.model.communityDetails.CommunityDetails
import com.example.domain.model.event.Meeting
import com.example.domain.repository.community.CommunityRepository
import com.example.domain.repository.event.EventRepository
import com.example.domain.usecase.details.GetCommunityDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCommunityDetails : KoinComponent {
    private val innerCommunityDetailInfo: GetCommunityDetailsUseCase by inject()
    private val communityRepository: CommunityRepository by inject()
    private val eventRepository: EventRepository by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val communityDetailsPrepared: Flow<CommunityDetails> =
        innerCommunityDetailInfo.communityIdTrigger().filterNotNull().mapLatest { communityId ->
            communityRepository.getCommunityDetails(communityId = communityId)
        }.flatMapMerge { it }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventsByCommunityId: Flow<List<Meeting>> =
        innerCommunityDetailInfo.communityIdTrigger().filterNotNull().mapLatest { communityId ->
            eventRepository.getEventsByCommunityId(communityId = communityId)
        }.flatMapMerge { it }

    fun execute() = communityDetailsPrepared

    fun invoke() = eventsByCommunityId
}