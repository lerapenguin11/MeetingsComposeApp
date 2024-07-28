package com.example.domain.usecase.details

import com.example.domain.model.CommunityDetails
import com.example.domain.model.CommunityMeetings
import com.example.domain.repository.CommunityDetailsRepository
import com.example.domain.stubs.CommunityDetailsStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
internal class GetCommunityByIdInteractorTest {

    @Mock
    private lateinit var repository : CommunityDetailsRepository
    private lateinit var getCommunityByIdUseCase : GetCommunityByIdUseCase
    private val communityDetailsStubs =  CommunityDetailsStubs()

    @BeforeEach
    fun setUp(){
        getCommunityByIdUseCase = GetCommunityByIdInteractor(repository = repository)
    }

    @Test
    fun `execute should return correct CommunityDetails`() = runBlocking {
        val communityId = communityDetailsStubs.communityId
        val expectedDetails = communityDetailsStubs.communityDetails()
        whenever(repository.getCommunityById(communityId)).thenReturn(expectedDetails)

        val result = getCommunityByIdUseCase.execute(communityId).first()

        assertEquals(expectedDetails, result)
    }

    @Test
    fun `execute should call repository with correct communityId`() = runBlocking {
        val communityId = communityDetailsStubs.communityId
        val communityDetails = communityDetailsStubs.communityDetails()
        `when`(repository.getCommunityById(communityId)).thenReturn(communityDetails)

        val result = getCommunityByIdUseCase.execute(communityId).first()

        verify(repository).getCommunityById(communityId)
        assertEquals(communityDetails, result)
    }
}