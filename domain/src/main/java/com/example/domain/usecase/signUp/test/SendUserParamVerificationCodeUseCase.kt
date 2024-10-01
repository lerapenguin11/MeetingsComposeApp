package com.example.domain.usecase.signUp.test

import com.example.domain.model.signUp.UserParam
import com.example.domain.repository.signUp.SignUpRepository

interface SendUserParamVerificationCodeUseCase {
    fun execute(param: UserParam)
}

internal class SendUserParamVerificationCodeUseCaseImpl(private val repository: SignUpRepository) :
    SendUserParamVerificationCodeUseCase {
    override fun execute(param: UserParam) {
        repository.getVerificationCode(userParam = param)
    }
}