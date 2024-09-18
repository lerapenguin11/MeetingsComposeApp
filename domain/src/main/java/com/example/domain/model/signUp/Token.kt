package com.example.domain.model.signUp

import com.example.common.result.SendCodeStatus

data class Token(
    val token: String?,
    val success: SendCodeStatus
)
