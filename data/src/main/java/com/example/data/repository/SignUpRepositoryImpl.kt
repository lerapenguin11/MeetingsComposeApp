package com.example.data.repository

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.common.result.SendCodeStatus
import com.example.data.mappers.SingUpMapper
import com.example.domain.model.signUp.Token
import com.example.domain.model.signUp.UserParam
import com.example.domain.repository.signUp.SignUpRepository
import com.example.network.api.AuthApi
import com.example.network.responseModel.auth.VerificationCode
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.retrofit.statusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SignUpRepositoryImpl(private val service: AuthApi, private val mapper: SingUpMapper) :
    SignUpRepository {

    override fun getVerificationCode(userParam: UserParam): Flow<PhoneNumberResult<PhoneNumberStatus>> {
        return flow {
            val response =
                service.getRequestCode(authParam = mapper.userParamToAuthParam(userParam = userParam))

            response.suspendOnSuccess {
                val responseCode = statusCode.code
                when (responseCode) {
                    SUCCESS_CODE -> emit(value = PhoneNumberResult.Success(PhoneNumberStatus.SUCCESS))
                    else -> emit(value = PhoneNumberResult.Error(Exception(PhoneNumberStatus.ERROR.message)))
                }
            }.onFailure {
                message()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun sendConfirmationCode(
        code: String,
        phoneNumber: String
    ): Flow<PhoneNumberResult<Token>> = flow {
        val response = service.checkCode(code = VerificationCode(code = code, phone = phoneNumber))
        response.suspendOnError {
            val responseCode = statusCode.code
            when (responseCode) {
                ERROR_CODE -> emit(
                    value = PhoneNumberResult.Success(
                        Token(
                            token = null,
                            success = SendCodeStatus.FAILURE
                        )
                    )
                )

                else -> emit(
                    value = PhoneNumberResult.Success(
                        Token(
                            token = null,
                            success = SendCodeStatus.ERROR
                        )
                    )
                )
            }
        }
        response.suspendOnSuccess {
            val responseCode = statusCode.code
            when (responseCode) {
                SUCCESS_CODE -> emit(
                    value = PhoneNumberResult.Success(
                        Token(
                            token = data.token,
                            success = SendCodeStatus.SUCCESS
                        )
                    )
                )
            }
        }.onFailure {
            message()
        }
    }.flowOn(Dispatchers.IO)
}

private const val SUCCESS_CODE = 200
private const val ERROR_CODE = 401