package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository

interface SaveIsEnableNotificationsUseCase {
    suspend fun execute(isOn: Boolean)
}

internal class SaveIsEnableNotificationsUseCaseImpl(private val repository: StoreRepository) :
    SaveIsEnableNotificationsUseCase {
    override suspend fun execute(isOn: Boolean) {
        repository.saveIsEnableNotifications(isOn = isOn)
    }
}