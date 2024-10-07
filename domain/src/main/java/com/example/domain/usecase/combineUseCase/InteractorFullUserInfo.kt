package com.example.domain.usecase.combineUseCase

import com.example.domain.model.user.UserInfo
import com.example.domain.model.userLists.UserCommunities
import com.example.domain.model.userLists.UserEvents
import com.example.domain.usecase.getData.GetUserCommunities
import com.example.domain.usecase.getData.GetUserEvents
import com.example.domain.usecase.getData.GetUserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullUserInfo : KoinComponent {
    private val getUserInfo: GetUserInfo by inject()
    private val getUserEvents: GetUserEvents by inject()
    private val getUserCommunities: GetUserCommunities by inject()

    private val fullUserInfoFlow: Flow<FullUserInfo> = combine(
        getUserInfo.execute(),
        getUserEvents.execute(),
        getUserCommunities.execute()
    ) { userInfo, userEvents, userCommunities ->
        FullUserInfo(
            userInfo = userInfo,
            userEvents = userEvents,
            userCommunities = userCommunities
        )
    }

    fun execute(): Flow<FullUserInfo> = fullUserInfoFlow
}

data class FullUserInfo(
    val userInfo: UserInfo?,
    val userEvents: List<UserEvents>?,
    val userCommunities: List<UserCommunities>?
)