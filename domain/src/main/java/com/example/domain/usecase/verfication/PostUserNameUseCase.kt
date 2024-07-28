package com.example.domain.usecase.verfication

import com.example.domain.model.CreateUser
import com.example.domain.repository.VerificationRepository

interface PostUserNameUseCase {
    fun execute(userName: CreateUser) //TODO
}

internal class PostUserNameInteractor(private val repository: VerificationRepository) : PostUserNameUseCase {
    override fun execute(userName: CreateUser) {
        repository.createUser(userName = userName)
    }
}