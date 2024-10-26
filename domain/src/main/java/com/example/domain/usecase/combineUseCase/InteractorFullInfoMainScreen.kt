package com.example.domain.usecase.combineUseCase

import com.example.domain.usecase.getData.GetCommunities
import com.example.domain.usecase.getData.GetEventsByCategory
import com.example.domain.usecase.getData.GetEventsClosest
import com.example.domain.usecase.interest.GetInterestsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullInfoMainScreen : KoinComponent {
    private val getEventsByCategory: GetEventsByCategory by inject()
    private val getEventsClosest: GetEventsClosest by inject()
    private val getCommunities: GetCommunities by inject()
    private val getInterestsUseCase: GetInterestsUseCase by inject()

    private val innerEventFlow = combine(
        getEventsByCategory.execute(),
        getEventsClosest.execute(),
        getCommunities.execute(),
        getInterestsUseCase.execute()

    ) { eventsByCategory, eventsClosest, communities, interests ->
        CombineMainDataScreen(
            eventsByCategory = eventsByCategory,
            eventsClosest = eventsClosest,
            communities = communities,
            categoryList = interests,
            isLoadingFullData = false
        )
    }

    fun execute(
    ): Flow<CombineMainDataScreen> = innerEventFlow
}

data class CombineMainDataScreen(
    val eventsByCategory: List<com.example.model.event.Meeting>,
    val eventsClosest: List<com.example.model.event.Meeting>,
    val communities: List<com.example.model.community.Community>,
    val categoryList: List<com.example.model.interest.Interest>,
    val isLoadingFullData: Boolean
)