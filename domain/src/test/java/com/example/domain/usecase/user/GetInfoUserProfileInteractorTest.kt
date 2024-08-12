package com.example.domain.usecase.user

import com.example.domain.model.Profile
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
internal class GetInfoUserProfileInteractorTest {

    @Mock
    private lateinit var repository: UserRepository
    private lateinit var getInfoUserProfileUseCase: GetInfoUserProfileUseCase
    private val userStubs = UserStubs()

    @BeforeEach
    fun setUp() {
        getInfoUserProfileUseCase = GetInfoUserProfileInteractor(repository = repository)
    }

    @Test
    fun `execute should call repository`() = runTest {
        val profile = userStubs.profile()

        whenever(repository.getInfoUserProfile()).thenReturn(profile)

        getInfoUserProfileUseCase.execute().first()

        verify(repository).getInfoUserProfile()
    }

    @Test
    fun `execute should return correct Profile`() = runTest {
        val expectedProfile = userStubs.profile()
        whenever(repository.getInfoUserProfile()).thenReturn(expectedProfile)

        val result = getInfoUserProfileUseCase.execute().first()

        assertEquals(expectedProfile, result)
    }
}