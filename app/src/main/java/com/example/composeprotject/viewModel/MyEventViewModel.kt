package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Event
import com.example.domain.usecase.event.GetMyEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyEventViewModel(private val getMyEventsUseCase: GetMyEventsUseCase) : ViewModel() {

    private val _myEvents = MutableStateFlow<List<Event>>(emptyList())
    private val myEvents: StateFlow<List<Event>> = _myEvents

    fun getMyEventsFlow() = myEvents

    fun getMyEvents(variant: String) = viewModelScope.launch {
        _myEvents.update { _ ->
            getMyEventsUseCase.execute(variantMyEvent = variant)
        }
    }
}