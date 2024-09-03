package com.example.domain.old.usecase.verfication

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.old.repository.VerificationRepository

interface PostPhoneNumberUseCase {
    fun execute(phoneNumber: String): PhoneNumberResult<PhoneNumberStatus>
}

internal class PostPhoneNumberInteractor(private val repository: VerificationRepository) :
    PostPhoneNumberUseCase {

    override fun execute(phoneNumber: String): PhoneNumberResult<PhoneNumberStatus> {
        return repository.postPhoneNumber(phoneNumber = phoneNumber)
    }
}