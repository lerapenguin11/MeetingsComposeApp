package com.example.domain.usecase.signUp.test

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow

interface GetUserParamSendStatusCode {
    fun execute(): Flow<PhoneNumberResult<PhoneNumberStatus>>
}

internal class GetUserParamSendStatusCodeImpl(private val repository: SignUpRepository) :
    GetUserParamSendStatusCode {
    override fun execute(): Flow<PhoneNumberResult<PhoneNumberStatus>> {
        return repository.resultGetVerificationCode()
    }
}