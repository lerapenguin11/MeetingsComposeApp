package com.example.composeprotject.domain.usecase.user

import com.example.composeprotject.domain.repository.UserRepository

class GetShortInfoUserUseCase(private val repository: UserRepository) {

    operator fun invoke() = repository.getShortInfoUser()
}