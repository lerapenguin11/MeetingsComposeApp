package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.allEvents.InteractorLoadRelevantEvents
import com.example.domain.usecase.allEvents.getData.GetRelevantEvents
import com.example.domain.usecase.combineUseCase.CombineFullQueryParamLocal
import com.example.domain.usecase.combineUseCase.InteractorFullQueryParamLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class AllEventsViewModel(
    private val interactorFullQueryParamLocal: InteractorFullQueryParamLocal,
    private val interactorLoadRelevantEvents: InteractorLoadRelevantEvents,
    private val getRelevantEvents: GetRelevantEvents
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val fullQueryParamLocal: StateFlow<CombineFullQueryParamLocal> =
        interactorFullQueryParamLocal.execute().flatMapLatest { fullParam ->
            flow {
                emit(value = fullParam)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CombineFullQueryParamLocal(
                userInterests = emptyList(),
                city = null,
                authToken = null
            )
        )

    val a = getRelevantEvents.execute().flatMapLatest { t ->
        flow {
            emit(value = t)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        a.launchIn(viewModelScope)
    }

    fun getEventsByCategoryFlow() = a
    fun getFullQueryParamLocalFlow() = fullQueryParamLocal

    fun loadData(queryParam: com.example.model.allEvents.RelevantEventsQueryParams) {
        interactorLoadRelevantEvents.execute(queryParam = queryParam)
    }

    private fun getEventsByCategory() {
        getRelevantEvents.execute()
            .onEach {

            }.launchIn(viewModelScope)


    }
}