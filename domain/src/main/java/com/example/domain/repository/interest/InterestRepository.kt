package com.example.domain.repository.interest

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface InterestRepository {

    @WorkerThread
    fun getInterests(): Flow<List<com.example.model.interest.Interest>>

    suspend fun addInterestsLocal(
        userInterests: List<com.example.model.interest.Interest>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    )

    fun getUserInterest(): Flow<List<Int>>

    /*

    fun addInterestsRemote(userInterests: List<Interest>)*/
}