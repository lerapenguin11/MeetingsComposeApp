package com.example.domain.usecase.signUp

import com.example.common.result.PhoneNumberResult
import com.example.domain.model.signUp.Token
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SendConfirmationCodeUseCase {
    fun execute(code: String): Flow<PhoneNumberResult<Token>>
}

internal class SendConfirmationCodeUseCaseImpl(private val repository: SignUpRepository) :
    SendConfirmationCodeUseCase {
    override fun execute(code: String): Flow<PhoneNumberResult<Token>> = flow {
        emit(value = repository.sendConfirmationCode(code = code))
    }
}