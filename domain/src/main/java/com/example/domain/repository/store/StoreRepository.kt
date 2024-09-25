package com.example.domain.repository.store

import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun saveOnBoardingInterestState(completed: Boolean)

    fun readOnBoardingInterestState(): Flow<Boolean>

    suspend fun saveUserCity(city: String?)

    fun readUserCity(): Flow<String?>

    suspend fun saveAuthToken(token: String)

    fun readeAuthToken(): Flow<String?>

    suspend fun saveIsShowMyCommunities(isOn: Boolean)

    fun readIsShowMyCommunities(): Flow<Boolean>

    suspend fun saveIsShowMyEvents(isOn: Boolean)

    fun readIsShowMyEvents(): Flow<Boolean>

    suspend fun saveIsEnableNotifications(isOn: Boolean)

    fun readIsEnableNotifications(): Flow<Boolean>
}