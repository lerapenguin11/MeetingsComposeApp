package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.event.EventListType
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.event.InteractorLoadEventsByCategory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val interactorLoadEventsByCategory: InteractorLoadEventsByCategory,
    private val interactorFullInfoMainScreen: InteractorFullInfoMainScreen
) : ViewModel() {

    private val fullInfoMainScreen: StateFlow<CombineMainDataScreen> =
        interactorFullInfoMainScreen.execute().flatMapLatest { test ->
            flow { emit(value = test) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CombineMainDataScreen(eventList = emptyList(), isLoading = true)
        )

    init {
        loadEventsByCategory()
    }

    fun getFullInfoMainScreen() = fullInfoMainScreen

    private fun loadEventsByCategory() {
        interactorLoadEventsByCategory.execute(
            eventType = EventListType.RELEVANT,
            userInterests = null,
            authToken = null
        )
    }
}