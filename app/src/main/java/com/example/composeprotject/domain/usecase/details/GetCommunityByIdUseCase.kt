package com.example.composeprotject.domain.usecase.details

import com.example.composeprotject.domain.repository.CommunityDetailsRepository

class GetCommunityByIdUseCase(private val repository: CommunityDetailsRepository) {

    operator fun invoke(communityId : Int) = repository.getCommunityById(communityId = communityId)
}