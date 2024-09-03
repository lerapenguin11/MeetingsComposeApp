package com.example.domain.old.usecase.verfication

import com.example.domain.old.model.CreateUser
import com.example.domain.old.repository.VerificationRepository

interface PostUserNameUseCase {
    fun execute(userName: CreateUser) //TODO
}

internal class PostUserNameInteractor(private val repository: VerificationRepository) :
    PostUserNameUseCase {
    override fun execute(userName: CreateUser) {
        repository.createUser(userName = userName)
    }
}