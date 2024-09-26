package com.example.domain.usecase.user

import com.example.domain.model.user.OptionsUploadingMyLists
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetUserInfoUseCase {

    private val streamUserInfoWithQueryParam = MutableStateFlow<OptionsUploadingMyLists?>(null)
    private var lastValue: OptionsUploadingMyLists? = null

    fun loadUserInfo(
        optionsUploading: OptionsUploadingMyLists
    ) {
        lastValue = optionsUploading
        streamUserInfoWithQueryParam.tryEmit(value = optionsUploading)
    }

    fun trigger(): SharedFlow<OptionsUploadingMyLists?> = streamUserInfoWithQueryParam
}

class InteractorLoadUserInfo : KoinComponent {
    private val innerUserInfo: GetUserInfoUseCase by inject()

    fun execute(optionsUploading: OptionsUploadingMyLists) {
        innerUserInfo.loadUserInfo(optionsUploading = optionsUploading)
    }
}