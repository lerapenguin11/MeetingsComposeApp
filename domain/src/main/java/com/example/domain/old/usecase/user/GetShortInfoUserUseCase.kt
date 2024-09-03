package com.example.domain.old.usecase.user

import com.example.domain.old.model.ShortInfoUser
import com.example.domain.old.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetShortInfoUserUseCase {
    fun execute(): Flow<ShortInfoUser>
}

internal class GetShortInfoUserInteractor(private val repository: UserRepository) :
    GetShortInfoUserUseCase {

    override fun execute() = flow { emit(repository.getShortInfoUser()) }
}