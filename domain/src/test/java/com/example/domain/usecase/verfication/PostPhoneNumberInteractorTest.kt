package com.example.domain.usecase.verfication

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.repository.VerificationRepository
import com.example.domain.stubs.VerificationStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
internal class PostPhoneNumberInteractorTest {

    @Mock
    private lateinit var repository: VerificationRepository
    private lateinit var postPhoneNumberUseCase: PostPhoneNumberUseCase
    private val verificationStubs = VerificationStubs()

    @BeforeEach
    fun setUp() {
        postPhoneNumberUseCase = PostPhoneNumberInteractor(repository = repository)
    }

    @Test
    fun `execute should return success when repository returns success`() {
        val phoneNumber = verificationStubs.phoneNumber
        val expectedResult = PhoneNumberResult.Success(PhoneNumberStatus.SUCCESS)

        whenever(repository.postPhoneNumber(phoneNumber)).thenReturn(expectedResult)

        val result = postPhoneNumberUseCase.execute(phoneNumber)

        assertEquals(expectedResult, result)
        verify(repository).postPhoneNumber(phoneNumber)
    }

    @Test
    fun `execute should return error when repository returns error`() {
        val phoneNumber = verificationStubs.phoneNumber
        val expectedResult = PhoneNumberResult.Error(Exception(verificationStubs.textException))

        whenever(repository.postPhoneNumber(phoneNumber)).thenReturn(expectedResult)

        val result = postPhoneNumberUseCase.execute(phoneNumber)

        assertEquals(expectedResult, result)
        verify(repository).postPhoneNumber(phoneNumber)
    }

    @Test
    fun `execute should call repository with correct phone number`() {
        val phoneNumber = verificationStubs.phoneNumber

        postPhoneNumberUseCase.execute(phoneNumber)

        verify(repository).postPhoneNumber(phoneNumber)
    }
}