package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.event.QueryParam
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.event.InteractorLoadMainInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val interactorLoadMainInfo: InteractorLoadMainInfo,
    private val interactorFullInfoMainScreen: InteractorFullInfoMainScreen
) : ViewModel() {

    private val fullInfoMainScreen: StateFlow<CombineMainDataScreen> =
        interactorFullInfoMainScreen.execute().flatMapLatest { fullInfo ->
            flow {
                _mainStateUI.update { (fullInfo.isLoadingFullData) }
                emit(value = fullInfo)
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CombineMainDataScreen(
                    eventsByCategory = emptyList(),
                    eventsClosest = emptyList(),
                    communities = emptyList(),
                    isLoadingFullData = true
                )
            )
    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    init {
        loadEventsByCategory()
    }

    fun getMainStateUI() = mainStateUI

    fun getFullInfoMainScreen() = fullInfoMainScreen

    private fun loadEventsByCategory() {
        interactorLoadMainInfo.execute(
            QueryParam(
                authToken = null,
                userInterests = null,
                city = null
            )
        )
    }
}