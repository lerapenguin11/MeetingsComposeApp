package com.example.domain.usecase.user

import com.example.domain.model.Profile
import com.example.domain.repository.UserRepository

interface GetInfoUserProfileUseCase {
    fun execute(): Profile
}

internal class GetInfoUserProfileInteractor(private val repository: UserRepository) :
    GetInfoUserProfileUseCase {

    override fun execute() = repository.getInfoUserProfile()
}