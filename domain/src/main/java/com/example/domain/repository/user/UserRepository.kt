package com.example.domain.repository.user

import com.example.domain.model.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserInfo(token: String): Flow<UserInfo>
}