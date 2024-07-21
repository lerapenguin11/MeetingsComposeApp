package com.example.domain.usecase.details

import com.example.domain.model.CommunityDetails
import com.example.domain.repository.CommunityDetailsRepository

interface GetCommunityByIdUseCase {
    fun execute(communityId: Int): CommunityDetails
}

internal class GetCommunityByIdInteractor(private val repository: CommunityDetailsRepository) :
    GetCommunityByIdUseCase {

    override fun execute(communityId: Int): CommunityDetails =
        repository.getCommunityById(communityId = communityId)
}