package com.example.domain.repository.interest

import androidx.annotation.WorkerThread
import com.example.domain.model.interest.Interest
import kotlinx.coroutines.flow.Flow

interface InterestRepository {

    @WorkerThread
    fun getInterests(): Flow<List<Interest>>

    suspend fun addInterestsLocal(
        userInterests: List<Interest>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    )

    /*

    fun addInterestsRemote(userInterests: List<Interest>)*/
}