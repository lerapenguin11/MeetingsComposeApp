package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository

interface SaveIsShowMyEventsUseCase {
    suspend fun execute(isOn: Boolean)
}

internal class SaveIsShowMyEventsUseCaseImpl(private val repository: StoreRepository) :
    SaveIsShowMyEventsUseCase {
    override suspend fun execute(isOn: Boolean) {
        repository.saveIsShowMyEvents(isOn = isOn)
    }
}