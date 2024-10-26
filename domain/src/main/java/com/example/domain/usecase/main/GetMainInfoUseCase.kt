package com.example.domain.usecase.main

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMainInfoUseCase {
    private val streamEventsWithQueryParam: MutableSharedFlow<com.example.model.event.QueryParam?> =
        MutableStateFlow(null)
    private var lastValue: com.example.model.event.QueryParam? = null

    fun loadMainInfo(
        queryParam: com.example.model.event.QueryParam
    ) {
        lastValue = queryParam
        streamEventsWithQueryParam.tryEmit(value = queryParam)
    }

    fun refresh() {
        lastValue?.run {
            streamEventsWithQueryParam.tryEmit(value = this)
        }
    }

    fun trigger(): SharedFlow<com.example.model.event.QueryParam?> =
        streamEventsWithQueryParam
}

class InteractorLoadMainInfo : KoinComponent {
    private val innerInfo: GetMainInfoUseCase by inject()
    fun execute(
        queryParam: com.example.model.event.QueryParam
    ) {
        innerInfo.loadMainInfo(
            queryParam = queryParam
        )
    }
}

class InteractorRefreshMainInfo : KoinComponent {
    private val innerMainInfo: GetMainInfoUseCase by inject()

    fun execute() = innerMainInfo.refresh()
}

