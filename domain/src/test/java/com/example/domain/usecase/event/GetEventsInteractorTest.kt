package com.example.domain.usecase.event

import com.example.common.utils_ui.EventVariant
import com.example.domain.repository.EventRepository
import com.example.domain.stubs.EventsStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
internal class GetEventsInteractorTest {

    @Mock
    private lateinit var repository: EventRepository
    private lateinit var getEventsUseCase: GetEventsUseCase
    private val eventsStubs = EventsStubs()

    @BeforeEach
    fun setUp() {
        getEventsUseCase = GetEventsInteractor(repository = repository)
    }

    @Test
    fun `execute should return a non-empty list of events from the repository if the option ALL EVENT`() =
        runTest {
            val expectedEvents = eventsStubs.events()

            //val fakeResult = flow { emit(expectedEvents) }

            whenever(repository.getAllEvent()).thenReturn(expectedEvents)

            val actualCommunities = getEventsUseCase.execute(EventVariant.ALL_EVENT.name)

            actualCommunities.collect {
                assert(it.isNotEmpty())
            }
        }

    @Test
    fun `execute should return a non-empty list of events from the repository if the option ACTIVE EVENT`() =
        runTest {
            val expectedEvents = eventsStubs.events()

            //val fakeResult = flow { emit(expectedEvents) }

            whenever(repository.getActiveEvent()).thenReturn(expectedEvents)

            val actualCommunities = getEventsUseCase.execute(EventVariant.ACTIVE_EVENT.name)

            actualCommunities.collect {
                assert(it.isNotEmpty())
            }
        }

    @Test
    fun `execute should return correct list of ALL EVENTS`() = runTest {
        val expectedCommunities = eventsStubs.events()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getAllEvent()).thenReturn(expectedCommunities)

        val actualCommunities = getEventsUseCase.execute(EventVariant.ALL_EVENT.name)

        assertEquals(expectedCommunities, actualCommunities.single())
    }

    @Test
    fun `execute should return correct list of ACTIVE EVENTS`() = runTest {
        val expectedCommunities = eventsStubs.events()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getActiveEvent()).thenReturn(expectedCommunities)

        val actualCommunities = getEventsUseCase.execute(EventVariant.ACTIVE_EVENT.name)

        assertEquals(expectedCommunities, actualCommunities.single())
    }
}