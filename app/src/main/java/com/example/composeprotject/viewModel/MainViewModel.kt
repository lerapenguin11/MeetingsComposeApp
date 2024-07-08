package com.example.composeprotject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeprotject.navigation.BottomNavItem

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableLiveData<BottomNavItem>(BottomNavItem.EventItem)
    val currentScreen: LiveData<BottomNavItem> = _currentScreen

    fun setCurrentScreen(screen: BottomNavItem) {
        _currentScreen.value = screen
    }
}