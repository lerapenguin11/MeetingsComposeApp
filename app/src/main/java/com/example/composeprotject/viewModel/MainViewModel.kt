package com.example.composeprotject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeprotject.navigation.BottomNavItem

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableLiveData<BottomNavItem>(BottomNavItem.Event)
    val currentScreen: LiveData<BottomNavItem> = _currentScreen

    private val _showTopBar = MutableLiveData<Boolean>(false)
    val showTopBar = _showTopBar

    private val _showBottomBar = MutableLiveData<Boolean>(false)
    val showBottomBar = _showBottomBar

    fun setCurrentScreen(screen: BottomNavItem) {
        _currentScreen.value = screen
    }

    fun setCurrentScreenShowTopBar(show : Boolean){
        _showTopBar.value = show
        _showBottomBar.value = show
    }
}