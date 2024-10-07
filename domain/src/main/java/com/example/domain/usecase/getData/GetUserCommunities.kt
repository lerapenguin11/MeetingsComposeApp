package com.example.domain.usecase.getData

import com.example.domain.model.userLists.UserCommunities
import com.example.domain.repository.userLists.UserListsRepository
import com.example.domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserCommunities : KoinComponent {
    private val innerUserInfo: GetUserInfoUseCase by inject()
    private val repository: UserListsRepository by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val userCommunitiesPrepared: Flow<List<UserCommunities>?> =
        innerUserInfo.trigger().mapLatest { loadingParam ->
            loadingParam?.let {
                if (it.isShowMyCommunities) {
                    repository.getUserCommunities()
                } else {
                    null
                }
            }
        }.flatMapMerge { it ?: flow { emptyList<UserCommunities>() } }

    fun execute(): Flow<List<UserCommunities>?> = userCommunitiesPrepared
}