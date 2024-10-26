package com.example.domain.repository.signUp

import com.example.model.signUp.Token
import com.example.result.PhoneNumberResult
import com.example.result.PhoneNumberStatus
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun sendConfirmationCode(
        code: String,
        phoneNumber: String
    ): Flow<PhoneNumberResult<Token>>

    fun getVerificationCode(userParam: com.example.model.signUp.UserParam): Flow<PhoneNumberResult<PhoneNumberStatus>>
}