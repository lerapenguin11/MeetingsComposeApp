package com.example.domain.usecase.combineUseCase

import com.example.domain.model.event.Meeting
import com.example.domain.usecase.event.GetEventsByCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullInfoMainScreen : KoinComponent {
    private val getEventsByCategory: GetEventsByCategory by inject()

    private val innerEventFlow = combine(
        getEventsByCategory.execute()
    ) { t1 ->
        CombineMainDataScreen(
            eventList = t1.reduce { _, flow -> flow }
        )
    }

    fun execute(): Flow<CombineMainDataScreen> = innerEventFlow
}

data class CombineMainDataScreen(
    val eventList: List<Meeting>,
    val isLoading: Boolean = false
)