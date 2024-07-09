package com.example.composeprotject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeprotject.navigation.NavItem

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableLiveData<NavItem>(NavItem.EventItem)
    val currentScreen: LiveData<NavItem> = _currentScreen

    private val _titleDetailedScreen = MutableLiveData<String>()
    val titleDetailedScreen: LiveData<String> = _titleDetailedScreen

    fun setCurrentScreen(screen: NavItem) {
        _currentScreen.value = screen
    }

    fun setTitleDetailedScreen(title: String) {
        _titleDetailedScreen.value = title
    }
}