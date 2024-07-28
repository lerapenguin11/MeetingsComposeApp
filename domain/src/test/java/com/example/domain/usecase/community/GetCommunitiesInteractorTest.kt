package com.example.domain.usecase.community

import com.example.domain.model.Community
import com.example.domain.repository.CommunityRepository
import com.example.domain.stubs.CommunitiesStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
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
internal class GetCommunitiesInteractorTest {

    @Mock
    private lateinit var repository: CommunityRepository
    private lateinit var getCommunitiesUseCase: GetCommunitiesUseCase
    private val communitiesStubs = CommunitiesStubs()

    @BeforeEach
    fun setUp() {
        getCommunitiesUseCase = GetCommunitiesInteractor(repository = repository)
    }

    @Test
    fun `execute should return non-empty list of communities from repository`() = runTest {
        val expectedCommunities = communitiesStubs.communities()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getCommunities()).thenReturn(expectedCommunities)

        val actualCommunities = getCommunitiesUseCase.execute()

        actualCommunities.collect {
            assert(it.isNotEmpty())
        }
    }

    @Test
    fun `execute should return empty list of communities from repository`() = runTest {
        val expectedCommunities = emptyList<Community>()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getCommunities()).thenReturn(expectedCommunities)

        val actualCommunities = getCommunitiesUseCase.execute()

        actualCommunities.collect {
            assert(it.isEmpty())
        }
    }

    @Test
    fun `execute should call getCommunities on repository`() = runTest {
        val fakeResult = flow { emit(emptyList<Community>()) }
        whenever(repository.getCommunities()).thenReturn(fakeResult.single())

        getCommunitiesUseCase.execute().single()

        verify(repository).getCommunities()
    }

    @Test
    fun `execute should return correct list of communities`() = runTest {
        val expectedCommunities = communitiesStubs.communities()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getCommunities()).thenReturn(expectedCommunities)

        val actualCommunities = getCommunitiesUseCase.execute()

        assertEquals(expectedCommunities, actualCommunities.single())
    }
}