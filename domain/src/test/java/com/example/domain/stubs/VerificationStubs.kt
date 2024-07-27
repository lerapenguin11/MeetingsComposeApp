package com.example.domain.stubs

import com.example.domain.model.CreateUser

internal class VerificationStubs {

    val code = "1234"
    val phoneNumber = "1234567890"
    val textException = "Ошибка"

    fun newUser() = CreateUser(
        name = "Ivan",
        surname = null
    )
}