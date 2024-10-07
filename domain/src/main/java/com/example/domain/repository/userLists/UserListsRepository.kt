package com.example.domain.repository.userLists

import com.example.domain.model.userLists.UserCommunities
import com.example.domain.model.userLists.UserEvents
import kotlinx.coroutines.flow.Flow

interface UserListsRepository {

    fun getUserEvents(): Flow<List<UserEvents>>

    fun getUserCommunities(): Flow<List<UserCommunities>>
}