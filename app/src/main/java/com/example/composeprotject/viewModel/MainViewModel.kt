package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.event.QueryParam
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.event.InteractorLoadFilteredEventsByCategory
import com.example.domain.usecase.event.InteractorLoadMainInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val interactorLoadMainInfo: InteractorLoadMainInfo,
    private val interactorFullInfoMainScreen: InteractorFullInfoMainScreen,
    private val interactorLoadFilteredEventsByCategory: InteractorLoadFilteredEventsByCategory
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
    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    private val _userSelectedCategories = MutableStateFlow<List<Interest>>(emptyList())
    private val userSelectedCategories: StateFlow<List<Interest>> = _userSelectedCategories

    init {
        loadEventsByCategory()
    }

    fun getUserSelectedCategories() = userSelectedCategories
    fun getMainStateUI() = mainStateUI

    fun getFullInfoMainScreen() = fullInfoMainScreen

    fun toggleUserCategory(id: Interest) {
        if (hasUserCategories(id)) {
            updateUserCategory(id)
        } else {
            deleteUserCategory(id)
        }
    }

    fun loadFilteredEventsByCategory(filterParam: List<Int>) {
        interactorLoadFilteredEventsByCategory.execute(categories = filterParam)
    }

    private fun hasUserCategories(interest: Interest): Boolean {
        return _userSelectedCategories.value.none { it.id == interest.id }
    }

    private fun updateUserCategory(interest: Interest) {
        _userSelectedCategories.update { currentInterests ->
            currentInterests + interest
        }
    }

    fun test() {
        _userSelectedCategories.update { emptyList() }
    }

    private fun deleteUserCategory(interest: Interest) {
        _userSelectedCategories.update { currentInterests ->
            currentInterests.filterNot { it.id == interest.id }
        }
    }

    private fun test1() {

    }

    private fun loadEventsByCategory() {
        interactorLoadMainInfo.execute(
            QueryParam(
                authToken = null,
                userInterests = null,
                city = null
            )
        )

    }
}