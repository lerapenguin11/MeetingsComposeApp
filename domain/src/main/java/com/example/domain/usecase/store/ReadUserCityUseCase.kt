package com.example.domain.usecase.store

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadUserCityUseCase {
    fun execute(): Flow<String?>
}

internal class ReadUserCityUseCaseImpl(private val repository: StoreRepository) :
    ReadUserCityUseCase {
    override fun execute(): Flow<String?> {
        return repository.readUserCity()
    }
}