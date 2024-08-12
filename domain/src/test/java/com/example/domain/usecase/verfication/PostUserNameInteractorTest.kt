package com.example.domain.usecase.verfication

import com.example.domain.repository.VerificationRepository
import com.example.domain.stubs.VerificationStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
internal class PostUserNameInteractorTest {

    @Mock
    private lateinit var repository: VerificationRepository
    private lateinit var postUserNameUseCase: PostUserNameUseCase
    private val verificationStubs = VerificationStubs()

    @BeforeEach
    fun setUp() {
        postUserNameUseCase = PostUserNameInteractor(repository = repository)
    }

    @Test
    fun `execute should call repository with correct new user`() {
        val newUser = verificationStubs.newUser()

        postUserNameUseCase.execute(userName = newUser)

        verify(repository).createUser(userName = newUser)
    }
}