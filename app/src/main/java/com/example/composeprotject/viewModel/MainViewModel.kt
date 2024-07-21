package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import com.example.composeprotject.navigation.NavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow<NavItem>(NavItem.EventItem)
    private val currentScreen: StateFlow<NavItem> = _currentScreen

    private val _titleDetailedScreen = MutableStateFlow<String>("")
    private val titleDetailedScreen: StateFlow<String> = _titleDetailedScreen

    private val _showTopBar = MutableStateFlow<Boolean>(false)
    private val showTopBar: StateFlow<Boolean> = _showTopBar

    private val _showBottomBar = MutableStateFlow<Boolean>(false)
    private val showBottomBar = _showBottomBar

    fun getCurrentScreenFlow(): StateFlow<NavItem> = currentScreen
    fun getTitleDetailedScreenFlow(): StateFlow<String> = titleDetailedScreen
    fun getShowTopBarFlow(): StateFlow<Boolean> = showTopBar
    fun getShowBottomBarFlow(): StateFlow<Boolean> = showBottomBar

    fun setCurrentScreen(screen: NavItem, showTopBar: Boolean, showBottomBar: Boolean) {
        _currentScreen.update { screen }
        _showTopBar.update { showTopBar }
        _showBottomBar.update { showBottomBar }
    }

    fun setTitleDetailedScreen(title: String) {
        _titleDetailedScreen.update { _ -> title }
    }
}