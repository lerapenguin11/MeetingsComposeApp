package com.example.domain.usecase.interest

import com.example.domain.model.interest.AddVariantInterests
import com.example.domain.model.interest.UserInterestDomain
import com.example.domain.repository.interest.InterestRepository

interface AddUserInterestsUseCase {
    suspend fun execute(
        userInterests: UserInterestDomain,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    )
}

internal class AddUserInterestsUseCaseInteractor(private val repository: InterestRepository) :
    AddUserInterestsUseCase {

    override suspend fun execute(
        userInterests: UserInterestDomain,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) {
        when (userInterests.addVariantInterests) {
            AddVariantInterests.LOCAL -> {
                repository.addInterestsLocal(
                    userInterests = userInterests.userInterest,
                    onStart = onStart,
                    onComplete = onComplete,
                    onError = onError
                )
            }

            AddVariantInterests.REMOTE_AND_LOCAL -> {
                //TODO
            }
        }
    }
}
