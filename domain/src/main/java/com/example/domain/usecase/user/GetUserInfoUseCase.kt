package com.example.domain.usecase.user

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetUserInfoUseCase {

    private val streamUserInfoWithQueryParam =
        MutableStateFlow<com.example.model.user.OptionsUploadingMyLists?>(null)
    private var lastValue: com.example.model.user.OptionsUploadingMyLists? = null

    fun loadUserInfo(
        optionsUploading: com.example.model.user.OptionsUploadingMyLists
    ) {
        lastValue = optionsUploading
        streamUserInfoWithQueryParam.tryEmit(value = optionsUploading)
    }

    fun trigger(): SharedFlow<com.example.model.user.OptionsUploadingMyLists?> =
        streamUserInfoWithQueryParam
}

class InteractorLoadUserInfo : KoinComponent {
    private val innerUserInfo: GetUserInfoUseCase by inject()

    fun execute(optionsUploading: com.example.model.user.OptionsUploadingMyLists) {
        innerUserInfo.loadUserInfo(optionsUploading = optionsUploading)
    }
}