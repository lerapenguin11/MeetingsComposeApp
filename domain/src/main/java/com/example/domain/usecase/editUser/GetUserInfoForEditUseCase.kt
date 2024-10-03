package com.example.domain.usecase.editUser

import com.example.domain.model.token.AutToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetUserInfoForEditUseCase {

    private val streamUserInfoWithQueryParam = MutableStateFlow<AutToken?>(null)
    private var lastValue: AutToken? = null

    fun loadUserInfo(
        token: AutToken
    ) {
        lastValue = token
        streamUserInfoWithQueryParam.tryEmit(value = token)
    }

    fun trigger(): SharedFlow<AutToken?> = streamUserInfoWithQueryParam
}

class InteractorLoadUserInfoForEdit : KoinComponent {
    private val innerUserInfoForEdit: GetUserInfoForEditUseCase by inject()

    fun execute(token: AutToken) = innerUserInfoForEdit.loadUserInfo(token = token)
}