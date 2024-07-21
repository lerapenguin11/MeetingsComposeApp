package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Event
import com.example.domain.usecase.event.GetMyEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyEventViewModel(private val getMyEventsUseCase: com.example.domain.usecase.event.GetMyEventsUseCase) : ViewModel() {

    private val _myEvents = MutableStateFlow<List<com.example.domain.model.Event>>(emptyList())
    private val myEvents: StateFlow<List<com.example.domain.model.Event>> = _myEvents

    fun getMyEventsFlow() = myEvents

    fun getMyEvents(variant: String) = viewModelScope.launch {
        _myEvents.emit(value = getMyEventsUseCase.getMyEvents(variantMyEvent = variant))
    }
}