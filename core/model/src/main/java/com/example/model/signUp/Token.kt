package com.example.model.signUp

import com.example.result.SendCodeStatus

data class Token(
    val token: String?,
    val success: SendCodeStatus
)
