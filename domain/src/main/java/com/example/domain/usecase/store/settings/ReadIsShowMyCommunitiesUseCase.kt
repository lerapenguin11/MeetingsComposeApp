package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadIsShowMyCommunitiesUseCase {
    fun execute(): Flow<Boolean>
}

internal class ReadIsIsShowMyCommunitiesUseCaseImpl(private val repository: StoreRepository) :
    ReadIsShowMyCommunitiesUseCase {
    override fun execute(): Flow<Boolean> {
        return repository.readIsShowMyCommunities()
    }
}