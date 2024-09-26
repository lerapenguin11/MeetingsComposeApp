package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.user.OptionsUploadingMyLists
import com.example.domain.usecase.combineUseCase.FullUserInfo
import com.example.domain.usecase.combineUseCase.InteractorFullUserInfo
import com.example.domain.usecase.combineUseCase.InteractorReadIsShowSettingsLists
import com.example.domain.usecase.combineUseCase.ShowSettingsMyLists
import com.example.domain.usecase.user.InteractorLoadUserInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    private val interactorReadIsShowSettingsLists: InteractorReadIsShowSettingsLists,
    private val getUserInfo: InteractorLoadUserInfo,
    private val interactorFullUserInfo: InteractorFullUserInfo
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
        getUserInfo.execute(
            optionsUploading = OptionsUploadingMyLists(
                isShowMyEvents = isShowMyEvents,
                isShowMyCommunities = isShowMyCommunities,
                authToken = authToken
            )
        )
    }
}