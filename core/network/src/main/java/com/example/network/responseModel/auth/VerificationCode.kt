package com.example.network.responseModel.auth

data class VerificationCode(
    val code: String,
    val phone: String
)