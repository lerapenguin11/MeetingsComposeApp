package com.example.domain.old.usecase.user

import com.example.domain.old.model.Profile
import com.example.domain.old.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetInfoUserProfileUseCase {
    fun execute(): Flow<Profile>
}

internal class GetInfoUserProfileInteractor(private val repository: UserRepository) :
    GetInfoUserProfileUseCase {

    override fun execute() = flow { emit(repository.getInfoUserProfile()) }
}