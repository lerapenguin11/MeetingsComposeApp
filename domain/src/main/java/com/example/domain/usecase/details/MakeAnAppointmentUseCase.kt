package com.example.domain.usecase.details

import com.example.common.result.ResultData
import com.example.domain.model.eventDetails.AppointmentSettings
import com.example.domain.repository.event.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface MakeAnAppointmentUseCase {
    fun execute(params: AppointmentSettings)
    fun resultRecordAnEvent(): Flow<ResultData<Boolean>>
}

internal class MakeAnAppointmentUseCaseImpl(private val repository: EventRepository) :
    MakeAnAppointmentUseCase {

    private val eventIdMutableStateFlow = MutableStateFlow<AppointmentSettings?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultRecordAnEventFlow =
        eventIdMutableStateFlow.flatMapLatest { param ->
            flow {
                param?.let {
                    //repository.makeAnAppointment(params = it)
                    when (it.isParticipatingMeeting) {
                        true -> emit(repository.skipMeetings(params = it))
                        false -> emit(repository.makeAnAppointment(params = it))
                    }
                }
            }
            /*param?.let {
                //repository.makeAnAppointment(params = it)
                when (it.isParticipatingMeeting) {
                    true -> repository.skipMeetings(params = it)
                    false -> repository.makeAnAppointment(params = it)
                }
            }*/
        }.flatMapMerge { it }

    init {
        resultRecordAnEventFlow.launchIn(CoroutineScope(Dispatchers.IO))
        resultRecordAnEventFlow.onEach {
            it
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun execute(params: AppointmentSettings) {
        eventIdMutableStateFlow.tryEmit(value = params)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun resultRecordAnEvent(): Flow<ResultData<Boolean>> {
        return resultRecordAnEventFlow/*.flatMapMerge { it ?: emptyFlow() }*/
    }
}