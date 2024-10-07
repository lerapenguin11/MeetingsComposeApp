package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository

interface SaveIsShowMyCommunitiesUseCase {
    suspend fun execute(isOn: Boolean)
}

internal class SaveIsShowMyCommunitiesUseCaseImpl(private val repository: StoreRepository) :
    SaveIsShowMyCommunitiesUseCase {
    override suspend fun execute(isOn: Boolean) {
        repository.saveIsShowMyCommunities(isOn = isOn)
    }
}