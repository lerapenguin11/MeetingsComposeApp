package com.example.data.repository

import com.example.data.mock.MockUserData
import com.example.domain.model.Profile
import com.example.domain.model.ShortInfoUser
import com.example.domain.repository.UserRepository

internal class UserRepositoryImpl(private val mock : MockUserData) :
    UserRepository {
    override fun getShortInfoUser(): ShortInfoUser {
        return mock.mockShortInfoUser()
    }

    override fun getInfoUserProfile(): Profile {
        return mock.mockInfoUserProfile()
    }
}