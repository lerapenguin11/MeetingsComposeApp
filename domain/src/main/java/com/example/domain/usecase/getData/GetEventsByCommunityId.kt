package com.example.domain.usecase.getData

import com.example.domain.model.event.Meeting
import com.example.domain.repository.community.CommunityRepository
import com.example.domain.usecase.details.GetEventDetailsInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetEventsByCommunityId : KoinComponent {
    private val innerEvents: GetEventDetailsInfoUseCase by inject()
    private val repository: CommunityRepository by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventsPrepared: Flow<List<Meeting>> =
        innerEvents.communityIdTrigger().mapLatest { communityId ->
            communityId?.let {
                repository.getEventsByCommunityId(communityId = it)
            }
        }.flatMapMerge { it ?: flow { emptyList<Meeting>() } }


    fun execute() = eventsPrepared
}