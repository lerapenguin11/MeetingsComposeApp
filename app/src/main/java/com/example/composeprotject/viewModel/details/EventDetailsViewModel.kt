package com.example.composeprotject.viewModel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.domain.model.EventDetails
import com.example.composeprotject.domain.usecase.details.GetEventByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventDetailsViewModel(private val getEventByIdUseCase: GetEventByIdUseCase) : ViewModel() {

    private val _isActionEventDetails = MutableStateFlow(false)
    private val isActionEventDetails: StateFlow<Boolean> = _isActionEventDetails

    private val _eventDetails = MutableStateFlow<EventDetails?>(null)
    private val eventDetails: StateFlow<EventDetails?> = _eventDetails

    fun getEventDetailsFlow(): StateFlow<EventDetails?> = eventDetails

    fun getIsActionEventDetailsFlow() = isActionEventDetails

    fun setActionEventDetails(isAction: Boolean) {
        _isActionEventDetails.value = isAction
    }

    fun getEventDetails(eventId: Int) = viewModelScope.launch {
        _eventDetails.emit(value = getEventByIdUseCase(eventId = eventId))
    }
}