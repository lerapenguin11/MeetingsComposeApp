package com.example.domain.usecase.signUp.test

import com.example.common.result.PhoneNumberResult
import com.example.common.result.PhoneNumberStatus
import com.example.domain.model.signUp.UserParam
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

interface SendUserParamAuthentication {
    fun execute(userParam: UserParam)
    fun resultSendUserParam(): Flow<PhoneNumberResult<PhoneNumberStatus>>
}

internal class GetUserParamSendUserParamAuthenticationImpl(private val repository: SignUpRepository) :
    SendUserParamAuthentication {

    private val userParamMutableStateFlow = MutableStateFlow<UserParam?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultSendUserParamFlow = userParamMutableStateFlow.mapLatest { userParam ->
        userParam?.let { repository.getVerificationCode(userParam = it) }
    }

    init {
        resultSendUserParamFlow.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun resultSendUserParam(): Flow<PhoneNumberResult<PhoneNumberStatus>> {
        return resultSendUserParamFlow.flatMapMerge { it ?: emptyFlow() }
    }

    override fun execute(userParam: UserParam) {
        userParamMutableStateFlow.tryEmit(value = userParam)
    }
}

