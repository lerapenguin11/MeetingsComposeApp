package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.combineUseCase.CombineEventDetailsInfo
import com.example.domain.usecase.combineUseCase.InteractorFullEventDetailsInfo
import com.example.domain.usecase.details.InteractorLoadEventDetailsInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val interactorFullEventDetailsInfo: InteractorFullEventDetailsInfo,
    private val interactorLoadEventDetailsInfo: InteractorLoadEventDetailsInfo,
) : ViewModel() {

    private val eventDetailsInfo: StateFlow<CombineEventDetailsInfo?> =
        interactorFullEventDetailsInfo.execute().filterNotNull()
            .flatMapLatest { eventDetailsInfo ->
                flow {
                    //_mainStateUI.update { (eventDetailsInfo.isLoadingFullData) }
                    emit(value = eventDetailsInfo)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CombineEventDetailsInfo(
                    eventDetails = null,
                    isLoadingFullData = true,
                    eventsByCommunityId = emptyList(),
                    authToken = null
                )
            )

    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    fun getEventDetailsInfo() = eventDetailsInfo

    fun loadEventDetailsInfo(eventId: Int) = viewModelScope.launch {
        interactorLoadEventDetailsInfo.execute(eventId = eventId)
    }
}