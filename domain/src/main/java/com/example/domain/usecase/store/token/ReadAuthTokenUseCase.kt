package com.example.domain.usecase.store.token

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadAuthTokenUseCase {
    fun execute(): Flow<String?>
}

internal class ReadAuthTokenUseCaseImpl(private val repository: StoreRepository) :
    ReadAuthTokenUseCase {
    override fun execute(): Flow<String?> {
        return repository.readeAuthToken()
    }
}