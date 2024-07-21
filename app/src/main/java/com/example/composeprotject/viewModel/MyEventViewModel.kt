package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.domain.model.nav.Event
import com.example.composeprotject.domain.usecase.event.GetMyEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyEventViewModel(private val getMyEventsUseCase: GetMyEventsUseCase) : ViewModel() {

    private val _myEvents = MutableStateFlow<List<Event>>(emptyList())
    private val myEvents: StateFlow<List<Event>> = _myEvents

    fun getMyEventsFlow() = myEvents

    fun getMyEvents(variant: String) = viewModelScope.launch {
        _myEvents.emit(value = getMyEventsUseCase.getMyEvents(variantMyEvent = variant))
    }
}