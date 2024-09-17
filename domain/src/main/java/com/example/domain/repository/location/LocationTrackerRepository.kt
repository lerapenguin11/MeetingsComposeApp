package com.example.domain.repository.location

interface LocationTrackerRepository {
    suspend fun getCurrentLocation(): String?
}