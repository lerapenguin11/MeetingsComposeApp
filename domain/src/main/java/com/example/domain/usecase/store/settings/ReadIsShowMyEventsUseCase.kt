package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadIsShowMyEventsUseCase {
    fun execute(): Flow<Boolean>
}

internal class ReadIsShowMyEventsUseCaseImpl(private val repository: StoreRepository) :
    ReadIsShowMyEventsUseCase {
    override fun execute(): Flow<Boolean> {
        return repository.readIsShowMyEvents()
    }
}