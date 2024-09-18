package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.event.QueryParam
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.event.InteractorLoadMainInfo
import com.example.domain.usecase.location.GetCurrentLocationUseCase
import com.example.domain.usecase.store.ReadUserCityUseCase
import com.example.domain.usecase.store.ReadeAuthTokenUseCase
import com.example.domain.usecase.store.SaveUserCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactorLoadMainInfo: InteractorLoadMainInfo,
    private val interactorFullInfoMainScreen: InteractorFullInfoMainScreen,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val saveUserCityUseCase: SaveUserCityUseCase,
    private val readUserCityUseCase: ReadUserCityUseCase,
    private val readeAuthTokenUseCase: ReadeAuthTokenUseCase
) : ViewModel() {

    private val _currentLocation = MutableStateFlow<String?>(null)
    private val currentLocation: StateFlow<String?> = _currentLocation

    private val fullInfoMainScreen: StateFlow<CombineMainDataScreen> =
        interactorFullInfoMainScreen.execute().flatMapLatest { fullInfo ->
            flow {
                _mainStateUI.update { (fullInfo.isLoadingFullData) }
                emit(value = fullInfo)
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CombineMainDataScreen(
                    eventsByCategory = emptyList(),
                    eventsClosest = emptyList(),
                    communities = emptyList(),
                    categoryList = emptyList(),
                    filteredEventsByCategory = emptyList(),
                    isLoadingFullData = true
                )
            )

    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    private val _userSelectedCategories = MutableStateFlow<List<Interest>>(emptyList())
    private val userSelectedCategories: StateFlow<List<Interest>> = _userSelectedCategories

    private val _authToken = MutableStateFlow<String?>(null)
    private val authToken: StateFlow<String?> = _authToken

    init {
        getAuthToken()
        updateCurrentLocation()
    }

    fun getAuthTokenFlow() = authToken
    fun getCurrentLocationFlow() = currentLocation
    fun getUserSelectedCategoriesFlow() = userSelectedCategories
    fun getMainStateUIFlow() = mainStateUI
    fun getFullInfoMainScreenFlow() = fullInfoMainScreen

    fun loadEventsByCategory(
        selectedCategory: List<Int>,
        city: String?,
        token: String?
    ) =
        viewModelScope.launch {
            interactorLoadMainInfo.execute(
                QueryParam(
                    authToken = token,
                    userInterests = null,
                    city = city,
                    filteredParam = selectedCategory
                )
            )
        }

    fun toggleUserCategory(id: Interest) {
        if (hasUserCategories(id)) {
            updateUserCategory(id)
        } else {
            deleteUserCategory(id)
        }
    }

    fun clearUserSelectedCategories() = viewModelScope.launch {
        _userSelectedCategories.update { emptyList() }
    }

    private fun hasUserCategories(interest: Interest): Boolean {
        return _userSelectedCategories.value.none { it.id == interest.id }
    }

    private fun updateUserCategory(interest: Interest) = viewModelScope.launch {
        _userSelectedCategories.update { currentInterests ->
            currentInterests + interest
        }
    }

    private fun deleteUserCategory(interest: Interest) = viewModelScope.launch {
        _userSelectedCategories.update { currentInterests ->
            currentInterests.filterNot { it.id == interest.id }
        }
    }

    private fun getAuthToken() {
        readeAuthTokenUseCase.execute().onEach { token ->
            _authToken.update { token }
        }.launchIn(scope = viewModelScope)
    }

    private fun updateCurrentLocation() {
        viewModelScope.launch {
            saveUserCityUseCase.execute(city = getCurrentLocationUseCase.execute())
        }
        readUserCityUseCase.execute().onEach { city ->
            _currentLocation.update { city }
        }.launchIn(viewModelScope)
    }

}