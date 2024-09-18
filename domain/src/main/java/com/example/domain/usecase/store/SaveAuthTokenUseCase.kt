package com.example.domain.usecase.store

import com.example.domain.repository.store.StoreRepository

interface SaveAuthTokenUseCase {
    suspend fun execute(token: String)
}

internal class SaveAuthTokenUseCaseImpl(private val repository: StoreRepository) :
    SaveAuthTokenUseCase {
    override suspend fun execute(token: String) {
        repository.saveAuthToken(token = token)
    }
}