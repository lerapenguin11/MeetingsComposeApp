package com.example.domain.repository.signUp

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.model.signUp.Token
import com.example.domain.model.signUp.UserParam
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    suspend fun sendConfirmationCode(
        code: String,
        phoneNumber: String
    ): Flow<PhoneNumberResult<Token>>

    fun getVerificationCode(userParam: UserParam): Flow<PhoneNumberResult<PhoneNumberStatus>>
}