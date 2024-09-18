package com.example.data.repository

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.common.result.SendCodeStatus
import com.example.domain.model.signUp.Token
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
                SUCCESS_CODE -> return@withContext PhoneNumberResult.Success(PhoneNumberStatus.SUCCESS)
                else -> return@withContext PhoneNumberResult.Error(Exception(PhoneNumberStatus.ERROR.message))
            }
        }

    override suspend fun sendConfirmationCode(code: String): PhoneNumberResult<Token> =
        withContext(Dispatchers.IO) {
            val responseCode = 200
            delay(3000)
            when (responseCode) {
                SUCCESS_CODE -> return@withContext PhoneNumberResult.Success(
                    Token(
                        token = null,
                        success = SendCodeStatus.SUCCESS
                    )
                )

                ERROR_CODE -> return@withContext PhoneNumberResult.Success(
                    Token(
                        token = null,
                        success = SendCodeStatus.FAILURE
                    )
                )

                else -> return@withContext PhoneNumberResult.Error(Exception())
            }
        }
}

private const val SUCCESS_CODE = 200
private const val ERROR_CODE = 401