package com.example.domain.usecase.store

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadOnBoardingInterestStateUseCase {
    fun execute(): Flow<Boolean>
}

internal class ReadOnBoardingInterestStateUseCaseImpl(private val repository: StoreRepository) :
    ReadOnBoardingInterestStateUseCase {

    override fun execute(): Flow<Boolean> {
        return repository.readOnBoardingInterestState()
    }
}