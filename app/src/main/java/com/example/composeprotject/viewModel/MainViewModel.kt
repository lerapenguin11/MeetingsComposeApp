package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.screen.state.SearchState
import com.example.domain.model.event.Meeting
import com.example.domain.model.event.QueryParam
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.combineUseCase.CombineFullQueryParamLocal
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.combineUseCase.InteractorFullQueryParamLocal
import com.example.domain.usecase.community.CommunitySubscriptionUseCase
import com.example.domain.usecase.getData.GetFilteredEventsByCategory
import com.example.domain.usecase.location.GetCurrentLocationUseCase
import com.example.domain.usecase.main.InteractorLoadFilteredEvents
import com.example.domain.usecase.main.InteractorLoadMainInfo
import com.example.domain.usecase.main.InteractorRefreshFilteredEvents
import com.example.domain.usecase.main.InteractorRefreshMainInfo
import com.example.domain.usecase.store.city.SaveUserCityUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactorFullQueryParamLocal: InteractorFullQueryParamLocal,
    private val interactorLoadMainInfo: InteractorLoadMainInfo,
    private val interactorFullInfoMainScreen: InteractorFullInfoMainScreen,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val saveUserCityUseCase: SaveUserCityUseCase,
    private val getFilteredEventsByCategory: GetFilteredEventsByCategory,
    private val loadFilteredEvents: InteractorLoadFilteredEvents,
    private val communitySubscriptionUseCase: CommunitySubscriptionUseCase,
    private val interactorRefreshMainInfo: InteractorRefreshMainInfo,
    private val interactorRefreshFilteredEvents: InteractorRefreshFilteredEvents
) : ViewModel() {

    private val _fullInfoMainScreen = MutableStateFlow(
        CombineMainDataScreen(
            eventsByCategory = emptyList(),
            eventsClosest = emptyList(),
            communities = emptyList(),
            categoryList = emptyList(),
            isLoadingFullData = true
        )
    )

    private val fullInfoMainScreen: StateFlow<CombineMainDataScreen> = _fullInfoMainScreen

    @OptIn(ExperimentalCoroutinesApi::class)
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

    private val _filteredEventsByCategory = MutableStateFlow<List<Meeting>>(emptyList())
    private val filteredEventsByCategory: StateFlow<List<Meeting>> = _filteredEventsByCategory

    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    private val _refreshMainInfoState: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    private val _refreshFilteredEventsByCategoryState: MutableStateFlow<Boolean?> =
        MutableStateFlow(null)

    private val _combineRefreshMainInfo = MutableStateFlow(false)
    private val combineRefreshMainInfo: StateFlow<Boolean> = _combineRefreshMainInfo

    private val _userSelectedCategories = MutableStateFlow<List<Interest>>(emptyList())
    private val userSelectedCategories: StateFlow<List<Interest>> = _userSelectedCategories

    private val _searchQuery = MutableStateFlow<String?>(null)
    private val searchQuery: StateFlow<String?> = _searchQuery

    private val _mainScreenState = MutableStateFlow(SearchState.MAIN_DEFAULT_SCREEN)
    private val mainScreenState: StateFlow<SearchState> = _mainScreenState

    /*private val _communitySubscriptions = MutableStateFlow<List<Community>>(emptyList())
    private val communitySubscriptions: StateFlow<List<Community>> = _communitySubscriptions*/

    init {
        getFullInfoMainScreen()
        getFilteredEventsByCategory()
        combineRefreshMainInfo()
    }

    fun getFullQueryParamLocalFlow() = fullQueryParamLocal
    fun getUserSelectedCategoriesFlow() = userSelectedCategories
    fun getMainStateUIFlow() = mainStateUI
    fun getFullInfoMainScreenFlow() = fullInfoMainScreen
    fun getSearchQuery() = searchQuery
    fun getMainScreenState() = mainScreenState
    fun getFilteredEvents() = filteredEventsByCategory
    fun getRefreshStateFlow() = combineRefreshMainInfo

    fun communitySubscription(communityId: Int, statusSubscription: Boolean, authToken: String) {
        communitySubscriptionUseCase.execute(communityId = communityId, authToken = authToken)
        updateCommunitySubscriptions(
            communityId = communityId,
            statusSubscription = statusSubscription
        )
    }

    fun refreshMainScreen() {
        _refreshMainInfoState.update { true }
        interactorRefreshMainInfo.execute()
        interactorRefreshFilteredEvents.execute()
        getFullInfoMainScreen()
        getFilteredEventsByCategory()
    }

    fun searchQueryUpdate(text: String?) {
        _searchQuery.update { text }
    }

    fun mainScreenStateUpdate(state: SearchState) {
        _mainScreenState.update {
            state
        }
    }

    fun loadEventsByCategory(
        userCategories: List<Int>,
        city: String?,
        token: String?
    ) =
        viewModelScope.launch {
            interactorLoadMainInfo.execute(
                QueryParam(
                    authToken = token,
                    userInterests = userCategories,
                    city = city
                )
            )
        }

    fun loadFilteredEvents(
        selectedCategory: List<Int>
    ) = viewModelScope.launch {
        loadFilteredEvents.execute(filterParam = selectedCategory)
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

    fun updateCurrentLocation() {
        viewModelScope.launch {
            saveUserCityUseCase.execute(city = getCurrentLocationUseCase.execute())
        }
    }

    private fun combineRefreshMainInfo() {
        combine(_refreshMainInfoState, _refreshFilteredEventsByCategoryState) { t1, t2 ->
            Pair(t1, t2)
        }.onEach {
            if (it.first == false && it.second == false) {
                updateCombineRefreshMainInfo(isRefreshing = false)
            }
        }.launchIn(viewModelScope)
    }

    private fun getFullInfoMainScreen() {
        interactorFullInfoMainScreen.execute().onEach { fullInfo ->
            _mainStateUI.update { (fullInfo.isLoadingFullData) }
            updateRefreshMainInfoState(isRefreshing = false)
            _fullInfoMainScreen.tryEmit(fullInfo)
        }.launchIn(viewModelScope)
    }

    private fun getFilteredEventsByCategory() {
        getFilteredEventsByCategory.execute().onEach { filteredEvents ->
            updateRefreshFilteredEventsByCategoryState(isRefreshing = false)
            _filteredEventsByCategory.tryEmit(filteredEvents)
        }.launchIn(viewModelScope)
    }

    private fun updateRefreshFilteredEventsByCategoryState(isRefreshing: Boolean) {
        _refreshFilteredEventsByCategoryState.update { isRefreshing }
    }

    private fun updateRefreshMainInfoState(isRefreshing: Boolean) {
        _refreshMainInfoState.update { isRefreshing }
    }

    private fun updateCombineRefreshMainInfo(isRefreshing: Boolean) {
        _combineRefreshMainInfo.update { isRefreshing }
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

    private fun updateCommunitySubscriptions(communityId: Int, statusSubscription: Boolean) {
        _fullInfoMainScreen.update { mainDataScreen ->
            val communities = mainDataScreen.communities.map { community ->
                if (community.id == communityId) {
                    community.copy(statusSubscription = !statusSubscription)
                } else {
                    community
                }
            }
            CombineMainDataScreen(
                eventsClosest = mainDataScreen.eventsClosest,
                eventsByCategory = mainDataScreen.eventsByCategory,
                communities = communities,
                categoryList = mainDataScreen.categoryList,
                isLoadingFullData = mainDataScreen.isLoadingFullData
            )
        }
    }
}