package com.example.domain.usecase.user

import com.example.domain.model.ShortInfoUser
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetShortInfoUserUseCase {
    fun execute(): Flow<ShortInfoUser>
}

internal class GetShortInfoUserInteractor(private val repository: UserRepository) :
    GetShortInfoUserUseCase {

    override fun execute() = flow { emit(repository.getShortInfoUser()) }
}