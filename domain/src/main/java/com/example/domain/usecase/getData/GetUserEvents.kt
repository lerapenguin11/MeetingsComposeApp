package com.example.domain.usecase.getData

import com.example.domain.repository.userLists.UserListsRepository
import com.example.domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserEvents : KoinComponent {
    private val innerUserInfo: GetUserInfoUseCase by inject()
    private val repository: UserListsRepository by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val userEventsPrepared: Flow<List<com.example.model.userLists.UserEvents>?> =
        innerUserInfo.trigger().mapLatest { loadingParam ->
            loadingParam?.let {
                if (it.isShowMyEvents) {
                    repository.getUserEvents()
                } else {
                    null
                }
            }
        }.flatMapMerge { it ?: flow { emptyList<com.example.model.userLists.UserEvents>() } }

    fun execute(): Flow<List<com.example.model.userLists.UserEvents>?> = userEventsPrepared
}