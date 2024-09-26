package com.example.domain.usecase.store.token

import com.example.domain.repository.store.StoreRepository

interface DeleteAuthTokenUseCase {
    suspend fun execute()
}

internal class DeleteAuthTokenUseCaseImpl(private val repository: StoreRepository) :
    DeleteAuthTokenUseCase {
    override suspend fun execute() {
        repository.deleteAuthToken()
    }
}