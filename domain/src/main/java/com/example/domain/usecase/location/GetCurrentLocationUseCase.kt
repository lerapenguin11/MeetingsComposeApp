package com.example.domain.usecase.location

import com.example.domain.repository.location.LocationTrackerRepository

interface GetCurrentLocationUseCase {
    suspend fun execute(): String?
}

internal class GetCurrentLocationUseCaseImpl(private val repository: LocationTrackerRepository) :
    GetCurrentLocationUseCase {
    override suspend fun execute(): String? {
        return repository.getCurrentLocation()
    }
}