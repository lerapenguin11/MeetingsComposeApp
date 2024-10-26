package com.example.domain.usecase.interest

import com.example.domain.repository.interest.InterestRepository

interface AddUserInterestsUseCase {
    suspend fun execute(
        userInterests: com.example.model.interest.UserInterestDomain,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    )
}

internal class AddUserInterestsUseCaseInteractor(private val repository: InterestRepository) :
    AddUserInterestsUseCase {

    override suspend fun execute(
        userInterests: com.example.model.interest.UserInterestDomain,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) {
        when (userInterests.addVariantInterests) {
            com.example.model.interest.AddVariantInterests.LOCAL -> {
                repository.addInterestsLocal(
                    userInterests = userInterests.userInterest,
                    onStart = onStart,
                    onComplete = onComplete,
                    onError = onError
                )
            }

            com.example.model.interest.AddVariantInterests.REMOTE_AND_LOCAL -> {
                //TODO
            }
        }
    }
}
