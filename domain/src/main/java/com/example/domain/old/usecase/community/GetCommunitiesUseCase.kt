package com.example.domain.old.usecase.community

import com.example.domain.old.model.Community
import com.example.domain.old.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetCommunitiesUseCase {
    fun execute(): Flow<List<Community>>
}

internal class GetCommunitiesInteractor(private val repository: CommunityRepository) :
    GetCommunitiesUseCase {

    override fun execute() = flow { emit(repository.getCommunities()) }
}