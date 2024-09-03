package com.example.composeprotject.viewModel_old

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.old.model.Event
import com.example.domain.old.usecase.event.GetMyEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class MyEventViewModel(private val getMyEventsUseCase: GetMyEventsUseCase) : ViewModel() {

    private val _myEvents = MutableStateFlow<List<Event>>(emptyList())
    private val myEvents: StateFlow<List<Event>> = _myEvents

    fun getMyEventsFlow() = myEvents

    fun getMyEvents(variant: String) {
        getMyEventsUseCase.execute(variantMyEvent = variant)
            .onEach { events ->
                _myEvents.update { events }
            }
            .launchIn(viewModelScope)
    }
}