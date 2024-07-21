package com.example.composeprotject.domain.usecase.user

import com.example.composeprotject.domain.repository.UserRepository

class GetInfoUserProfileUseCase(private val repository: UserRepository) {

    operator fun invoke() = repository.getInfoUserProfile()
}