package com.example.domain.usecase.editUser

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetUserInfoForEditUseCase {

    private val streamUserInfoWithQueryParam =
        MutableStateFlow<com.example.model.token.AutToken?>(null)
    private var lastValue: com.example.model.token.AutToken? = null

    fun loadUserInfo(
        token: com.example.model.token.AutToken
    ) {
        lastValue = token
        streamUserInfoWithQueryParam.tryEmit(value = token)
    }

    fun trigger(): SharedFlow<com.example.model.token.AutToken?> = streamUserInfoWithQueryParam
}

class InteractorLoadUserInfoForEdit : KoinComponent {
    private val innerUserInfoForEdit: GetUserInfoForEditUseCase by inject()

    fun execute(token: com.example.model.token.AutToken) =
        innerUserInfoForEdit.loadUserInfo(token = token)
}