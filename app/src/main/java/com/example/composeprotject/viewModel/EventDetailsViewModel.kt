package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.result.ResultData
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.domain.model.eventDetails.AppointmentSettings
import com.example.domain.model.eventDetails.EventDetailsParams
import com.example.domain.usecase.combineUseCase.CombineEventDetailsInfo
import com.example.domain.usecase.combineUseCase.InteractorFullEventDetailsInfo
import com.example.domain.usecase.details.InteractorLoadEventDetailsInfo
import com.example.domain.usecase.details.MakeAnAppointmentUseCase
import com.example.domain.usecase.store.token.ReadAuthTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val interactorFullEventDetailsInfo: InteractorFullEventDetailsInfo,
    private val interactorLoadEventDetailsInfo: InteractorLoadEventDetailsInfo,
    private val makeAnAppointmentUseCase: MakeAnAppointmentUseCase,
    private val readAuthTokenUseCase: ReadAuthTokenUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val eventDetailsInfo: StateFlow<CombineEventDetailsInfo?> =
        interactorFullEventDetailsInfo.execute()
            .filterNotNull()
            .flatMapLatest { eventDetailsInfo ->
                flow {
                    //_mainStateUI.update { (eventDetailsInfo.isLoadingFullData) }
                    eventDetailsInfo.eventDetails?.isParticipating?.let {
                        updateActionBlockState(
                            state = if (it) FilledButtonState.ACTIVE_SECONDARY
                            else FilledButtonState.ACTIVE_PRIMARY
                        )
                        _isParticipatingMeeting.tryEmit(value = it)
                    }
                    emit(value = eventDetailsInfo)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CombineEventDetailsInfo(
                    eventDetails = null,
                    isLoadingFullData = true,
                    eventsByCommunityId = emptyList(),
                    authToken = null
                )
            )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val authToken: StateFlow<String?> = readAuthTokenUseCase.execute()
        .flatMapLatest { token ->
            flow {
                emit(value = token)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultMakeAppointment =
        makeAnAppointmentUseCase.resultRecordAnEvent().flatMapLatest { result ->
            flow {
                when (result) {
                    is ResultData.Success -> {
                        val blockState =
                            if (result.status) FilledButtonState.ACTIVE_SECONDARY else FilledButtonState.ACTIVE_PRIMARY
                        updateIsParticipatingMeeting(isParticipating = result.status)
                        updateActionBlockState(state = blockState)
                    }

                    is ResultData.Error -> {
                        //TODO
                    }
                }
                when (result) {
                    ResultData.Success(true) -> {
                        updateIsParticipatingMeeting(isParticipating = true)
                        updateActionBlockState(state = FilledButtonState.ACTIVE_SECONDARY)
                    }

                    ResultData.Success(false) -> {
                        updateIsParticipatingMeeting(isParticipating = false)
                        updateActionBlockState(state = FilledButtonState.ACTIVE_PRIMARY)
                    }

                    else -> {}
                }
                emit(value = result)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    private val _mainStateUI = MutableStateFlow(true)
    private val mainStateUI: StateFlow<Boolean> = _mainStateUI

    private val _actionBlockState = MutableStateFlow(FilledButtonState.ACTIVE_PRIMARY)
    private val actionBlockState: StateFlow<FilledButtonState> = _actionBlockState

    private val _isParticipatingMeeting = MutableStateFlow(false)
    private val isParticipatingMeeting: StateFlow<Boolean> = _isParticipatingMeeting

    init {
        resultMakeAppointment.launchIn(viewModelScope)
    }

    fun getAuthTokenFlow() = authToken
    fun getEventDetailsInfo() = eventDetailsInfo
    fun getActionBlockStateFlow() = actionBlockState
    fun getIsParticipatingMeetingFlow() = isParticipatingMeeting

    fun loadEventDetailsInfo(params: EventDetailsParams) = viewModelScope.launch {
        interactorLoadEventDetailsInfo.execute(params = params)
    }

    fun updateActionBlockState(state: FilledButtonState) {
        _actionBlockState.update { state }
    }

    fun makeAppointment(params: AppointmentSettings) {
        makeAnAppointmentUseCase.execute(params = params)
    }

    private fun updateIsParticipatingMeeting(isParticipating: Boolean) {
        _isParticipatingMeeting.update {
            isParticipating
        }
    }
}