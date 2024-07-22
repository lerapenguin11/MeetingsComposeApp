package com.example.domain.usecase.community

import com.example.domain.model.Community
import com.example.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetCommunitiesUseCase {
    fun execute(): Flow<List<Community>>
}

internal class GetCommunitiesInteractor(private val repository: CommunityRepository) :
    GetCommunitiesUseCase {

    override fun execute() = flow { emit(repository.getCommunities()) }
}