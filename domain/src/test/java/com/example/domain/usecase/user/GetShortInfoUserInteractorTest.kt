package com.example.domain.usecase.user

import com.example.domain.model.ShortInfoUser
import com.example.domain.repository.UserRepository
import com.example.domain.stubs.UserStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
internal class GetShortInfoUserInteractorTest {

    @Mock
    private lateinit var repository: UserRepository
    private lateinit var getShortInfoUserUseCase: GetShortInfoUserUseCase
    private val userStubs = UserStubs()

    @BeforeEach
    fun setUp() {
        getShortInfoUserUseCase = GetShortInfoUserInteractor(repository = repository)
    }

    @Test
    fun `execute should call repository`() = runTest {
        val profile = userStubs.shortInfoUser()

        whenever(repository.getShortInfoUser()).thenReturn(profile)

        getShortInfoUserUseCase.execute().first()

        verify(repository).getShortInfoUser()
    }

    @Test
    fun `execute should return correct ShortInfoUser`() = runTest {
        val expectedProfile = userStubs.shortInfoUser()
        whenever(repository.getShortInfoUser()).thenReturn(expectedProfile)

        val result = getShortInfoUserUseCase.execute().first()

        assertEquals(expectedProfile, result)
    }
}