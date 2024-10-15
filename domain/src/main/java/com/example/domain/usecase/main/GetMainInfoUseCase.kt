package com.example.domain.usecase.main

import com.example.domain.model.event.QueryParam
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetMainInfoUseCase {

    private val streamEventsWithQueryParam: MutableSharedFlow<QueryParam?> =
        MutableStateFlow(null)
    private var lastValue: QueryParam? = null

    fun loadMainInfo(
        queryParam: QueryParam
    ) {
        lastValue = queryParam
        streamEventsWithQueryParam.tryEmit(value = queryParam)
    }

    fun refresh() {
        lastValue?.run { streamEventsWithQueryParam.tryEmit(this) }
    }

    fun trigger(): SharedFlow<QueryParam?> =
        streamEventsWithQueryParam

}

class InteractorLoadMainInfo : KoinComponent {
    private val innerInfo: GetMainInfoUseCase by inject()
    fun execute(
        queryParam: QueryParam
    ) {
        innerInfo.loadMainInfo(
            queryParam = queryParam
        )
    }
}

class InteractorRefreshMainInfo : KoinComponent {
    private val innerMainInfo: GetMainInfoUseCase by inject()
    fun execute() {
        innerMainInfo.refresh()
    }
}

