package com.example.domain.usecase.store.city

import com.example.domain.repository.store.StoreRepository

interface SaveUserCityUseCase {
    suspend fun execute(city: String?)
}

internal class SaveUserCityUseCaseImpl(private val repository: StoreRepository) :
    SaveUserCityUseCase {
    override suspend fun execute(city: String?) {
        repository.saveUserCity(city = city)
    }
}