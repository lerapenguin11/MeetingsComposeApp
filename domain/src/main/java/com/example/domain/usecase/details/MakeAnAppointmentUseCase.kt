package com.example.domain.usecase.details

import com.example.common.result.ResultData
import com.example.common.result.ResultStatus
import com.example.domain.model.eventDetails.EventDetailsParams
import com.example.domain.repository.event.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

interface MakeAnAppointmentUseCase {
    fun execute(params: EventDetailsParams)
    fun resultRecordAnEvent(): Flow<ResultData<ResultStatus>>
}

internal class MakeAnAppointmentUseCaseImpl(private val repository: EventRepository) :
    MakeAnAppointmentUseCase {

    private val eventIdMutableStateFlow = MutableStateFlow<EventDetailsParams?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultRecordAnEventFlow = eventIdMutableStateFlow.mapLatest { userParam ->
        userParam?.let { repository.makeAnAppointment(params = it) }
    }

    init {
        resultRecordAnEventFlow.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun execute(params: EventDetailsParams) {
        eventIdMutableStateFlow.tryEmit(value = params)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun resultRecordAnEvent(): Flow<ResultData<ResultStatus>> {
        return resultRecordAnEventFlow.flatMapMerge { it ?: emptyFlow() }
    }
}