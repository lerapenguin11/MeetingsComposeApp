package com.example.domain.repository.signUp

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.model.signUp.UserParam

interface SignUpRepository {
    suspend fun getVerificationCode(userParam: UserParam): PhoneNumberResult<PhoneNumberStatus>
}