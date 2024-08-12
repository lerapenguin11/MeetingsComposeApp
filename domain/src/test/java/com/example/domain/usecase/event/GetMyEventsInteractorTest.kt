package com.example.domain.usecase.event

import com.example.common.utils_ui.MyEventVariant
import com.example.domain.model.Event
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
internal class GetMyEventsInteractorTest {

    @Mock
    private lateinit var repository: EventRepository
    private lateinit var getMyEventsUseCase: GetMyEventsUseCase
    private val eventsStubs = EventsStubs()

    @BeforeEach
    fun setUp() {
        getMyEventsUseCase = GetMyEventsInteractor(repository = repository)
    }

    @Test
    fun `execute should return a non-empty list of my events from the repository if the option ACTIVE EVENT`() =
        runTest {
            val expectedEvents = eventsStubs.events()

            //val fakeResult = flow { emit(expectedEvents) }

            whenever(repository.getMyActiveEvent()).thenReturn(expectedEvents)

            val actualCommunities = getMyEventsUseCase.execute(MyEventVariant.ACTIVE_EVENT.name)

            actualCommunities.collect {
                assert(it.isNotEmpty())
            }
        }

    @Test
    fun `execute should return a non-empty list of my events from the repository if the option INACTIVE EVENT`() =
        runTest {
            val expectedEvents = eventsStubs.events()

            //val fakeResult = flow { emit(expectedEvents) }

            whenever(repository.getMyInactiveEvent()).thenReturn(expectedEvents)

            val actualCommunities = getMyEventsUseCase.execute(MyEventVariant.INACTIVE_EVENT.name)

            actualCommunities.collect {
                assert(it.isNotEmpty())
            }
        }

    @Test
    fun `execute should return correct list of my INACTIVE EVENT`() = runTest {
        val expectedCommunities = eventsStubs.events()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getMyInactiveEvent()).thenReturn(expectedCommunities)

        val actualCommunities = getMyEventsUseCase.execute(MyEventVariant.INACTIVE_EVENT.name)

        assertEquals(expectedCommunities, actualCommunities.single())
    }

    @Test
    fun `execute should return correct list of my ACTIVE EVENT`() = runTest {
        val expectedCommunities = eventsStubs.events()
        //val fakeResult = flow { emit(expectedCommunities) }
        whenever(repository.getMyActiveEvent()).thenReturn(expectedCommunities)

        val actualCommunities = getMyEventsUseCase.execute(MyEventVariant.ACTIVE_EVENT.name)

        assertEquals(expectedCommunities, actualCommunities.single())
    }
}