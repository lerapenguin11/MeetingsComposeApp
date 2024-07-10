package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import com.example.composeprotject.navigation.NavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow<NavItem>(NavItem.EventItem)
    val currentScreen: StateFlow<NavItem> = _currentScreen

    private val _titleDetailedScreen = MutableStateFlow<String>("")
    val titleDetailedScreen: StateFlow<String> = _titleDetailedScreen //TODO

    private val _showTopBar = MutableStateFlow<Boolean>(false)
    val showTopBar = _showTopBar

    private val _showBottomBar = MutableStateFlow<Boolean>(false)
    val showBottomBar = _showBottomBar

    fun setCurrentScreen(screen: NavItem, show: Boolean) {
        _currentScreen.value = screen
        _showTopBar.value = show
        _showBottomBar.value = show
    }

    fun setTitleDetailedScreen(title: String) {
        _titleDetailedScreen.value = title
    }
}