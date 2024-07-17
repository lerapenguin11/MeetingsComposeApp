package com.example.composeprotject.domain.usecase.community

import com.example.composeprotject.domain.repository.CommunityRepository

class GetCommunitiesUseCase(private val repository: CommunityRepository) {

    operator fun invoke() = repository.getCommunities()
}