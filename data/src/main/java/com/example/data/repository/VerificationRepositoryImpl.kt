package com.example.data.repository

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.model.CreateUser
import com.example.domain.repository.VerificationRepository

internal class VerificationRepositoryImpl : VerificationRepository {

    override fun postPhoneNumber(phoneNumber: String) : PhoneNumberResult<PhoneNumberStatus> {
        return try {
            if (phoneNumber.isNotEmpty()){
                PhoneNumberResult.Success(status = PhoneNumberStatus.SUCCESS)
            }else{
                PhoneNumberResult.Success(status = PhoneNumberStatus.ERROR)
            }

        }catch (e : Exception){
            PhoneNumberResult.Error(e)
        }
    }

    override fun postCode(code: String) {
        //TODO("Not yet implemented")
    }

    override fun createUser(userName: CreateUser) {
        //TODO("Not yet implemented")
    }
}