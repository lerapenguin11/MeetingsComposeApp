package com.example.composeprotject.domain.usecase

import com.example.composeprotject.domain.repository.CommunityRepository

class GetCommunitiesUseCase(private val repository: CommunityRepository) {

    operator fun invoke() = repository.getCommunities()
}