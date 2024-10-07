package com.example.domain.usecase.getData

import com.example.domain.model.editUser.EditUserInfo
import com.example.domain.repository.editUser.EditUserRepository
import com.example.domain.usecase.editUser.GetUserInfoForEditUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserInfoForEdit : KoinComponent {
    private val repository: EditUserRepository by inject()
    private val innerUserInfoForEdit: GetUserInfoForEditUseCase by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val userInfoForEditPrepared: Flow<EditUserInfo> =
        innerUserInfoForEdit.trigger().mapLatest { autToken ->
            autToken?.let {
                repository.getUserInfoForEdit(token = it)
            }
        }.flatMapMerge { it ?: emptyFlow() }

    fun execute(): Flow<EditUserInfo> = userInfoForEditPrepared
}