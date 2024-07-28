package com.example.domain.usecase.details

import com.example.domain.repository.EventDetailsRepository
import com.example.domain.stubs.EventDetailsStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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
internal class GetEventByIdInteractorTest {

    @Mock
    private lateinit var repository: EventDetailsRepository
    private lateinit var getEventByIdUseCase: GetEventByIdUseCase
    private val eventDetailsStubs = EventDetailsStubs()

    @BeforeEach
    fun setUp() {
        getEventByIdUseCase = GetEventByIdInteractor(repository = repository)
    }

    @Test
    fun `execute should return correct EventDetails`() = runBlocking {
        val eventId = eventDetailsStubs.eventId
        val expectedDetails = eventDetailsStubs.eventDetails()

        whenever(repository.getEventById(eventId)).thenReturn(expectedDetails)

        val result = getEventByIdUseCase.execute(eventId).first()

        assertEquals(expectedDetails, result)
    }

    @Test
    fun `execute should call repository with correct eventId`() = runBlocking {
        val eventId = eventDetailsStubs.eventId
        val expectedDetails = eventDetailsStubs.eventDetails()

        whenever(repository.getEventById(eventId)).thenReturn(expectedDetails)

        val result = getEventByIdUseCase.execute(eventId).first()

        verify(repository).getEventById(eventId)
        assertEquals(expectedDetails, result)
    }
}