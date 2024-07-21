package com.example.domain.usecase.details

import com.example.domain.repository.CommunityDetailsRepository

class GetCommunityByIdUseCase(private val repository: CommunityDetailsRepository) {

    operator fun invoke(communityId : Int) = repository.getCommunityById(communityId = communityId)
}