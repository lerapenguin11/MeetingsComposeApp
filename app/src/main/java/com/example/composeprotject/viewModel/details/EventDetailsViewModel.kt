package com.example.composeprotject.viewModel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.details.GetEventByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventDetailsViewModel(private val getEventByIdUseCase: GetEventByIdUseCase) : ViewModel() {

    private val _isActionEventDetails = MutableStateFlow(false)
    private val isActionEventDetails: StateFlow<Boolean> = _isActionEventDetails

    private val _eventDetails = MutableStateFlow<com.example.domain.model.EventDetails?>(null)
    private val eventDetails: StateFlow<com.example.domain.model.EventDetails?> = _eventDetails

    fun getEventDetailsFlow(): StateFlow<com.example.domain.model.EventDetails?> = eventDetails

    fun getIsActionEventDetailsFlow() = isActionEventDetails

    fun setActionEventDetails(isAction: Boolean) {
        _isActionEventDetails.update { _ ->
            isAction
        }
    }

    fun getEventDetails(eventId: Int) {
        _eventDetails.update { getEventByIdUseCase.execute(eventId = eventId) }
    }
}