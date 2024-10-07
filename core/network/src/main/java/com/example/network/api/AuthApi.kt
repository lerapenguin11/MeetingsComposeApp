package com.example.network.api

import com.example.network.responseModel.auth.AuthParam
import com.example.network.responseModel.auth.AuthToken
import com.example.network.responseModel.auth.VerificationCode
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/request-code")
    suspend fun getRequestCode(@Body authParam: AuthParam): ApiResponse<Void>

    @POST("auth/check-code")
    suspend fun checkCode(@Body code: VerificationCode): ApiResponse<AuthToken>
}