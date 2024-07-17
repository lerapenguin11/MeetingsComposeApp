package com.example.composeprotject.viewModel.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.domain.model.nav.Event
import com.example.composeprotject.domain.usecase.event.GetEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel(private val getEventsUseCase: GetEventsUseCase) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    private val events: StateFlow<List<Event>> = _events

    fun getEventsFlow() = events

    fun getEvents(variantEvent: String) = viewModelScope.launch {
        _events.emit(value = getEventsUseCase.getEvents(variantEvent = variantEvent))
    }
}