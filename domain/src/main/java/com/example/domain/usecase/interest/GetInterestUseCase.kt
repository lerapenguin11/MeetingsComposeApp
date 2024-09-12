package com.example.domain.usecase.interest

import com.example.domain.model.interest.Interest
import com.example.domain.repository.interest.InterestRepository
import kotlinx.coroutines.flow.Flow

interface GetInterestsUseCase {
    fun execute(): Flow<List<Interest>>
}

internal class GetInterestInteractor(private val repository: InterestRepository) :
    GetInterestsUseCase {

    override fun execute(): Flow<List<Interest>> {
        return repository.getInterests()
    }
}
