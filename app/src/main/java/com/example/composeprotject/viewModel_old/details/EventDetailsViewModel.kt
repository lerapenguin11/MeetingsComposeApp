package com.example.composeprotject.viewModel_old.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.old.model.EventDetails
import com.example.domain.old.usecase.details.GetEventByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class EventDetailsViewModel(private val getEventByIdUseCase: GetEventByIdUseCase) : ViewModel() {

    private val _isActionEventDetails = MutableStateFlow(false)
    private val isActionEventDetails: StateFlow<Boolean> = _isActionEventDetails

    private val _eventDetails = MutableStateFlow<EventDetails?>(null)
    private val eventDetails: StateFlow<EventDetails?> = _eventDetails

    fun getEventDetailsFlow(): StateFlow<EventDetails?> = eventDetails

    fun getIsActionEventDetailsFlow() = isActionEventDetails

    fun setActionEventDetails(isAction: Boolean) {
        _isActionEventDetails.update { isAction }
    }

    fun getEventDetails(eventId: Int) {
        getEventByIdUseCase.execute(eventId = eventId)
            .onEach { details ->
                _eventDetails.update { details }
            }
            .launchIn(viewModelScope)
    }
}