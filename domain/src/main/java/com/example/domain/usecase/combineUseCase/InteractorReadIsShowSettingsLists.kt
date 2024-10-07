package com.example.domain.usecase.combineUseCase

import com.example.domain.usecase.store.settings.ReadIsShowMyCommunitiesUseCase
import com.example.domain.usecase.store.settings.ReadIsShowMyEventsUseCase
import com.example.domain.usecase.store.token.ReadAuthTokenUseCase
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorReadIsShowSettingsLists : KoinComponent {
    private val readIsShowMyCommunitiesUseCase: ReadIsShowMyCommunitiesUseCase by inject()
    private val readIsShowMyEventsUseCase: ReadIsShowMyEventsUseCase by inject()
    private val readAuthTokenUseCase: ReadAuthTokenUseCase by inject()

    private val isShowSettingsMyListsFlow = combine(
        readIsShowMyEventsUseCase.execute(),
        readIsShowMyCommunitiesUseCase.execute(),
        readAuthTokenUseCase.execute()
    ) { isShowMyEvents, isShowMyCommunities, authToken ->
        ShowSettingsMyLists(
            isShowMyCommunities = isShowMyCommunities,
            isShowMyEvents = isShowMyEvents,
            authToken = authToken
        )
    }

    fun execute() = isShowSettingsMyListsFlow
}

data class ShowSettingsMyLists(
    val isShowMyEvents: Boolean,
    val isShowMyCommunities: Boolean,
    val authToken: String?
)