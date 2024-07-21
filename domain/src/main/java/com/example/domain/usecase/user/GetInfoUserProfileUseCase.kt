package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository

class GetInfoUserProfileUseCase(private val repository: UserRepository) {

    operator fun invoke() = repository.getInfoUserProfile()
}