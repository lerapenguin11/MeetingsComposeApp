package com.example.domain.repository.signUp

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun sendConfirmationCode(
        code: String,
        phoneNumber: String
    ): Flow<PhoneNumberResult<com.example.model.signUp.Token>>

    fun getVerificationCode(userParam: com.example.model.signUp.UserParam): Flow<PhoneNumberResult<PhoneNumberStatus>>
}