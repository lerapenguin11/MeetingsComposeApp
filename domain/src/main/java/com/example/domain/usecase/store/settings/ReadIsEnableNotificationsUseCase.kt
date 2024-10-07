package com.example.domain.usecase.store.settings

import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

interface ReadIsEnableNotificationsUseCase {
    fun execute(): Flow<Boolean>
}

internal class ReadIsEnableNotificationsUseCaseImpl(private val repository: StoreRepository) :
    ReadIsEnableNotificationsUseCase {
    override fun execute(): Flow<Boolean> {
        return repository.readIsEnableNotifications()
    }
}