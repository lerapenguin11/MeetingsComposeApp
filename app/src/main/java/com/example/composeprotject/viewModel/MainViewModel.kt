package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.event.QueryParam
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.combineUseCase.CombineFullQueryParamLocal
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.combineUseCase.InteractorFullQueryParamLocal
import com.example.domain.usecase.event.InteractorLoadMainInfo
import com.example.domain.usecase.location.GetCurrentLocationUseCase
import com.example.domain.usecase.store.SaveUserCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactorFullQueryParamLocal: InteractorFullQueryParamLocal,
    private val interactorLoadMainInfo: InteractorLoadMainInfo,
    private val interactorFullInfoMainScreen: InteractorFullInfoMainScreen,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val saveUserCityUseCase: SaveUserCityUseCase
) : ViewModel() {

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

    private val fullQueryParamLocal: StateFlow<CombineFullQueryParamLocal> =
        interactorFullQueryParamLocal.execute().flatMapLatest { fullParam ->
            flow {
                emit(value = fullParam)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CombineFullQueryParamLocal(
                userInterests = emptyList(),
                city = null,
                authToken = null
            )
        )

    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    private val _userSelectedCategories = MutableStateFlow<List<Interest>>(emptyList())
    private val userSelectedCategories: StateFlow<List<Interest>> = _userSelectedCategories

    fun getFullQueryParamLocalFlow() = fullQueryParamLocal
    fun getUserSelectedCategoriesFlow() = userSelectedCategories
    fun getMainStateUIFlow() = mainStateUI
    fun getFullInfoMainScreenFlow() = fullInfoMainScreen

    fun loadEventsByCategory(
        userCategories: List<Int>,
        selectedCategory: List<Int>,
        city: String?,
        token: String?
    ) =
        viewModelScope.launch {
            interactorLoadMainInfo.execute(
                QueryParam(
                    authToken = token,
                    userInterests = userCategories,
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

    fun updateCurrentLocation() {
        viewModelScope.launch {
            saveUserCityUseCase.execute(city = getCurrentLocationUseCase.execute())
        }
    }
}