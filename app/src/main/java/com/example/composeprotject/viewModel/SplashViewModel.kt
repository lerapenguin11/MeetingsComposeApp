package com.example.composeprotject.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.nav.route.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class SplashViewModel : ViewModel() {

    private val _startDestination: MutableState<String> = mutableStateOf(Graph.LOCATION)
    private val startDestination: MutableState<String> = _startDestination

    private val _locationPermissionGranted = MutableStateFlow(false)
    private val locationPermissionGranted: StateFlow<Boolean> = _locationPermissionGranted

    init {
        getLocationState()
    }

    fun getStartDestination() = startDestination

    fun updateLocationPermissionGranted(isLocationPermission: Boolean) {
        _locationPermissionGranted.update { isLocationPermission }
    }

    private fun getLocationState() {
        locationPermissionGranted.onEach {
            if (it) {
                _startDestination.value = Graph.MAIN
            } else {
                _startDestination.value = Graph.LOCATION
            }
        }.launchIn(scope = viewModelScope)
    }
}