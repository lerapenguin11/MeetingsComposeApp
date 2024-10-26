package com.example.domain.repository.userLists

import kotlinx.coroutines.flow.Flow

interface UserListsRepository {

    fun getUserEvents(): Flow<List<com.example.model.userLists.UserEvents>>

    fun getUserCommunities(): Flow<List<com.example.model.userLists.UserCommunities>>
}