package com.example.data.repository

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.model.signUp.UserParam
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SignUpRepositoryImpl : SignUpRepository {
    override suspend fun getVerificationCode(userParam: UserParam): PhoneNumberResult<PhoneNumberStatus> =
        withContext(Dispatchers.IO) {
            val response = true
            val responseCode = 200
            delay(3000)
            when (responseCode) {
                200 -> return@withContext PhoneNumberResult.Success(PhoneNumberStatus.SUCCESS)
                else -> return@withContext PhoneNumberResult.Error(Exception(PhoneNumberStatus.ERROR.message))
            }
        }
}