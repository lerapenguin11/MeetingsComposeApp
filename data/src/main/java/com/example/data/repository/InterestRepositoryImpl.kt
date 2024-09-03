package com.example.data.repository

import androidx.annotation.WorkerThread
import com.example.data.fakeData.interestsFake
import com.example.data.mappers.InterestsMapper
import com.example.domain.model.interest.Interest
import com.example.domain.repository.interest.InterestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class InterestRepositoryImpl(
    private val mapper: InterestsMapper
) : InterestRepository {

    @WorkerThread
    override fun getInterests(): Flow<List<Interest>> {

        return flow {
            emit(value = interestsFake().map {
                mapper.responseInterestToInterest(it)
            })
        }
    }
}