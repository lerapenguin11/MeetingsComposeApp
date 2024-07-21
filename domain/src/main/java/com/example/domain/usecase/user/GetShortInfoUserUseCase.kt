package com.example.domain.usecase.user

import com.example.domain.model.ShortInfoUser
import com.example.domain.repository.UserRepository

interface GetShortInfoUserUseCase {
    fun execute(): ShortInfoUser
}

internal class GetShortInfoUserInteractor(private val repository: UserRepository) :
    GetShortInfoUserUseCase {

    override fun execute() = repository.getShortInfoUser()
}