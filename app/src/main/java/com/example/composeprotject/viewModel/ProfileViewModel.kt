package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.user.OptionsUploadingMyLists
import com.example.domain.usecase.combineUseCase.FullUserInfo
import com.example.domain.usecase.combineUseCase.InteractorFullUserInfo
import com.example.domain.usecase.combineUseCase.InteractorReadIsShowSettingsLists
import com.example.domain.usecase.combineUseCase.ShowSettingsMyLists
import com.example.domain.usecase.store.token.DeleteAuthTokenUseCase
import com.example.domain.usecase.user.InteractorLoadUserInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val interactorReadIsShowSettingsLists: InteractorReadIsShowSettingsLists,
    private val interactorLoadUserInfo: InteractorLoadUserInfo,
    private val interactorFullUserInfo: InteractorFullUserInfo,
    private val deleteAuthTokenUseCase: DeleteAuthTokenUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val isShowSettingsMyLists =
        interactorReadIsShowSettingsLists.execute().flatMapLatest {
            flow {
                emit(value = it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ShowSettingsMyLists(
                isShowMyCommunities = true,
                isShowMyEvents = true,
                authToken = null
            )
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val fullUserInfo = interactorFullUserInfo.execute().flatMapLatest {
        flow {
            emit(value = it)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FullUserInfo(
            userInfo = null,
            userCommunities = null,
            userEvents = null
        )
    )

    fun getIsShowSettingsMyListsFlow() = isShowSettingsMyLists
    fun getFullUserInfoFlow() = fullUserInfo

    fun loadUserInfo(isShowMyEvents: Boolean, isShowMyCommunities: Boolean, authToken: String) {
        interactorLoadUserInfo.execute(
            optionsUploading = OptionsUploadingMyLists(
                isShowMyEvents = isShowMyEvents,
                isShowMyCommunities = isShowMyCommunities,
                authToken = authToken
            )
        )
    }

    fun deleteAuthToken() = viewModelScope.launch {
        deleteAuthTokenUseCase.execute()
    }
}