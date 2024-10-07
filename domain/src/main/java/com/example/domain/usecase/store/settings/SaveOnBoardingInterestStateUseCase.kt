package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository

interface SaveOnBoardingInterestStateUseCase {
    suspend fun execute(completed: Boolean)
}

internal class SaveOnBoardingInterestStateUseCaseImpl(private val repository: StoreRepository) :
    SaveOnBoardingInterestStateUseCase {

    override suspend fun execute(completed: Boolean) {
        repository.saveOnBoardingInterestState(completed = completed)
    }
}