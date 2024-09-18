package com.example.domain.usecase.store

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadeAuthTokenUseCase {
    fun execute(): Flow<String?>
}

internal class ReadeAuthTokenUseCaseImpl(private val repository: StoreRepository) :
    ReadeAuthTokenUseCase {
    override fun execute(): Flow<String?> {
        return repository.readeAuthToken()
    }
}