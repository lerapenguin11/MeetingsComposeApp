package com.example.data.repository

import androidx.annotation.WorkerThread
import com.example.data.fakeData.interestsFake
import com.example.data.mappers.InterestsMapper
import com.example.database.dao.UserInterestDao
import com.example.domain.model.interest.Interest
import com.example.domain.repository.interest.InterestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

internal class InterestRepositoryImpl(
    private val mapper: InterestsMapper,
    private val dao: UserInterestDao
) : InterestRepository {

    @WorkerThread
    override fun getInterests(): Flow<List<Interest>> {

        return flow {
            emit(value = interestsFake().map {
                mapper.responseInterestToInterest(it)
            })
        }
    }

    override suspend fun addInterestsLocal(
        userInterests: List<Interest>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Unit = withContext(Dispatchers.IO) {
        val listAddedId = dao.insertAllInterest(
            interest = userInterests.map {
                mapper.interestToEntity(
                    interest = it
                )
            }.toTypedArray()
        )
        if (listAddedId.size == userInterests.size) {
            onComplete()
        } else {
            onError("Возникла ошибка при добавлении")
        }
    }
}