package com.example.domain.repository.store

import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun saveOnBoardingInterestState(completed: Boolean)

    fun readOnBoardingInterestState(): Flow<Boolean>

    suspend fun saveUserCity(city: String?)

    fun readUserCity(): Flow<String?>
}