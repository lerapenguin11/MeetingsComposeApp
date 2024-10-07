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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addInterestsLocal(
        userInterests: List<Interest>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Unit = withContext(Dispatchers.IO) {
        val listAddedId = dao.insertAllUserInterests(
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

    override fun getUserInterest(): Flow<List<Int>> {
        return dao.getUserInterests().flowOn(Dispatchers.IO).map {
            it.map { entity ->
                mapper.userInterestEntityToIdInterest(entity = entity)
            }
        }
    }
}