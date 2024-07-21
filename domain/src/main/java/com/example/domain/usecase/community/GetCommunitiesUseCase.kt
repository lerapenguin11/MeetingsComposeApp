package com.example.domain.usecase.community

import com.example.domain.repository.CommunityRepository

class GetCommunitiesUseCase(private val repository: CommunityRepository) {

    operator fun invoke() = repository.getCommunities()
}