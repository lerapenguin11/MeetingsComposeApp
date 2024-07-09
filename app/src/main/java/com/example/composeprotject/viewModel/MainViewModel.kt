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

    private val _showTopBar = MutableLiveData<Boolean>(false)
    val showTopBar = _showTopBar

    private val _showBottomBar = MutableLiveData<Boolean>(false)
    val showBottomBar = _showBottomBar

    fun setCurrentScreen(screen: NavItem, show: Boolean) {
        _currentScreen.value = screen
        _showTopBar.value = show
        _showBottomBar.value = show
    }

    fun setTitleDetailedScreen(title: String) {
        _titleDetailedScreen.value = title
    }

    /*fun setCurrentScreenShowTopBar(show : Boolean){

    }*/
}