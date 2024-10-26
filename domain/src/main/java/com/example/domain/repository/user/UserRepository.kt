package com.example.domain.repository.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserInfo(token: String): Flow<com.example.model.user.UserInfo>
}