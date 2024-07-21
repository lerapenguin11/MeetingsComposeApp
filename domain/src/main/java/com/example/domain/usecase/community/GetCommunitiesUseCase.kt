package com.example.domain.usecase.community

import com.example.domain.model.Community
import com.example.domain.repository.CommunityRepository

interface GetCommunitiesUseCase {
    fun execute(): List<Community>
}

internal class GetCommunitiesInteractor(private val repository: CommunityRepository) :
    GetCommunitiesUseCase {

    override fun execute() = repository.getCommunities()
}