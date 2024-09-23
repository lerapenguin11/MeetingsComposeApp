package com.example.domain.usecase.signUp

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.model.signUp.UserParam
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow

interface SendPhoneVerificationCodeUseCase {
    suspend fun execute(param: UserParam): Flow<PhoneNumberResult<PhoneNumberStatus>>
}

internal class SendPhoneVerificationCodeUseCaseImpl(private val repository: SignUpRepository) :
    SendPhoneVerificationCodeUseCase {
    override suspend fun execute(param: UserParam): Flow<PhoneNumberResult<PhoneNumberStatus>> {
        return repository.getVerificationCode(userParam = param)
    }
}