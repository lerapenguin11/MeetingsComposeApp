package com.example.composeprotject.viewModel_old.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Event
import com.example.domain.usecase.event.GetEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class EventViewModel(private val getEventsUseCase: GetEventsUseCase) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    private val events: StateFlow<List<Event>> = _events

    fun getEventsFlow() = events

    fun getEvents(variantEvent: String) {
        getEventsUseCase.execute(variantEvent = variantEvent)
            .onEach { events ->
                _events.update { events }
            }
            .launchIn(viewModelScope)
    }
}