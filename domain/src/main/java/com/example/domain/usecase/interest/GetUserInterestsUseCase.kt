package com.example.domain.usecase.interest

import com.example.domain.repository.interest.InterestRepository
import kotlinx.coroutines.flow.Flow

interface GetUserInterestsUseCase {
    fun execute(): Flow<List<Int>>
}

internal class GetUserInterestsUseCaseImpl(private val repository: InterestRepository) :
    GetUserInterestsUseCase {
    override fun execute(): Flow<List<Int>> {
        return repository.getUserInterest()
    }
}