package com.example.domain.old.usecase.details

import com.example.domain.old.model.CommunityDetails
import com.example.domain.old.repository.CommunityDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetCommunityByIdUseCase {
    fun execute(communityId: Int): Flow<CommunityDetails>
}

internal class GetCommunityByIdInteractor(private val repository: CommunityDetailsRepository) :
    GetCommunityByIdUseCase {

    override fun execute(communityId: Int): Flow<CommunityDetails> =
        flow { emit(repository.getCommunityById(communityId = communityId)) }
}