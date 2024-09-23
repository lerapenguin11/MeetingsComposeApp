package com.example.domain.usecase.signUp

import com.example.common.result.PhoneNumberResult
import com.example.domain.model.signUp.Token
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow

interface SendConfirmationCodeUseCase {
    suspend fun execute(code: String, phoneNumber: String): Flow<PhoneNumberResult<Token>>
}

internal class SendConfirmationCodeUseCaseImpl(private val repository: SignUpRepository) :
    SendConfirmationCodeUseCase {
    override suspend fun execute(
        code: String,
        phoneNumber: String
    ): Flow<PhoneNumberResult<Token>> {
        return repository.sendConfirmationCode(code = code, phoneNumber = phoneNumber)
    }
}