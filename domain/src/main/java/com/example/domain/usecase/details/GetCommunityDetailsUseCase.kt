package com.example.domain.usecase.details

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetCommunityDetailsUseCase {
    private val streamCommunityWithCommunityId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private var communityIdLastValue: Int? = null

    fun loadCommunityDetailsByCommunityId(communityId: Int) {
        communityIdLastValue = communityId
        streamCommunityWithCommunityId.tryEmit(communityId)
    }

    fun refresh() {
        communityIdLastValue?.run { streamCommunityWithCommunityId.tryEmit(this) }
    }

    fun communityIdTrigger(): SharedFlow<Int?> = streamCommunityWithCommunityId
}

class InteractorLoadCommunityDetails : KoinComponent {
    private val innerCommunityDetailsInfo: GetCommunityDetailsUseCase by inject()

    fun execute(communityId: Int) =
        innerCommunityDetailsInfo.loadCommunityDetailsByCommunityId(communityId = communityId)
}