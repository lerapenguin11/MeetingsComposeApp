package com.example.domain.usecase.getData

import com.example.domain.model.user.UserInfo
import com.example.domain.repository.user.UserRepository
import com.example.domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserInfo : KoinComponent {
    private val repository: UserRepository by inject()
    private val innerUserInfo: GetUserInfoUseCase by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val userInfoPrepared: Flow<UserInfo> =
        innerUserInfo.trigger().mapLatest { options ->
            options?.let {
                repository.getUserInfo(token = it.authToken)
            }
        }.flatMapMerge { it ?: flow { } }

    fun execute(): Flow<UserInfo> = userInfoPrepared
}