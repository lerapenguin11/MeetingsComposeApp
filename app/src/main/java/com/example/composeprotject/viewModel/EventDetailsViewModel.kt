package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EventDetailsViewModel : ViewModel() {

    private val _isActionEventDetails = MutableStateFlow(false)
    val isActionEventDetails: StateFlow<Boolean> = _isActionEventDetails

    fun setActionEventDetails(isAction : Boolean){
        _isActionEventDetails.value = isAction
    }
}