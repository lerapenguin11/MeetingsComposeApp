package com.example.domain.old.usecase.verfication

import com.example.domain.old.repository.VerificationRepository

interface PostCodeUseCase {
    fun execute(code: String) //TODO
}

internal class PostCodeInteractor(private val repository: VerificationRepository) :
    PostCodeUseCase {
    override fun execute(code: String) {
        repository.postCode(code = code)
    }
}