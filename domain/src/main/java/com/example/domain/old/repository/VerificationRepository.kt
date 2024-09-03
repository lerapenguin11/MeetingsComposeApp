package com.example.domain.old.repository

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.old.model.CreateUser

interface VerificationRepository {

    fun postPhoneNumber(phoneNumber : String) : PhoneNumberResult<PhoneNumberStatus>//TODO: вернуть результат запроса

    fun postCode(code : String) //TODO: вернуть результат запроса

    fun createUser(userName : CreateUser) //TODO: вернуть результат запроса
}