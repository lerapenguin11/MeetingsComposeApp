package com.example.domain.usecase.signUp

import com.example.common.result.PhoneNumberResult
import com.example.domain.model.signUp.Token
import com.example.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

interface SendConfirmationCodeUseCase {
    fun execute(code: String, phoneNumber: String)
    fun resultSendConfirmationCode(): Flow<PhoneNumberResult<Token>>
}

internal class SendConfirmationCodeUseCaseImpl(private val repository: SignUpRepository) :
    SendConfirmationCodeUseCase {
    private val confirmationCodeMutableStateFlow = MutableStateFlow<Pair<String, String>?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultConfirmationCodeFlow = confirmationCodeMutableStateFlow.mapLatest { param ->
        param?.let {
            repository.sendConfirmationCode(
                code = param.first,
                phoneNumber = param.second
            )
        }
    }

    init {
        resultConfirmationCodeFlow.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun execute(code: String, phoneNumber: String) {
        confirmationCodeMutableStateFlow.tryEmit(value = Pair(code, phoneNumber))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun resultSendConfirmationCode(): Flow<PhoneNumberResult<Token>> {
        return resultConfirmationCodeFlow.flatMapMerge { it ?: emptyFlow() }
    }
}