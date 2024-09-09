package com.example.domain.usecase.combineUseCase

import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.getData.GetCommunity
import com.example.domain.usecase.getData.GetEventsByCategory
import com.example.domain.usecase.getData.GetEventsClosest
import com.example.domain.usecase.getData.GetFilteredEventsByCategory
import com.example.domain.usecase.interest.GetInterestsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullInfoMainScreen : KoinComponent {
    private val getEventsByCategory: GetEventsByCategory by inject()
    private val getEventsClosest: GetEventsClosest by inject()
    private val getCommunity: GetCommunity by inject()
    private val getInterestsUseCase: GetInterestsUseCase by inject()
    private val getFilteredEventsByCategory: GetFilteredEventsByCategory by inject()

    private val innerEventFlow = combine(
        getEventsByCategory.execute(),
        getEventsClosest.execute(),
        getCommunity.execute(),
        getInterestsUseCase.execute(),
        getFilteredEventsByCategory.execute()

    ) { t1, t2, t3, t4, t5 ->
        CombineMainDataScreen(
            eventsByCategory = t1,
            eventsClosest = t2,
            communities = t3,
            categoryList = t4,
            filteredEventsByCategory = t5,
            isLoadingFullData = false
        )
    }

    fun execute(
    ): Flow<CombineMainDataScreen> = innerEventFlow
}

data class CombineMainDataScreen(
    val eventsByCategory: List<Meeting>,
    val eventsClosest: List<Meeting>,
    val communities: List<Community>,
    val categoryList: List<Interest>,
    val filteredEventsByCategory: List<Meeting>,
    val isLoadingFullData: Boolean
)