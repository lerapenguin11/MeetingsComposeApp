package com.example.data.old.repository

import com.example.data.old.mock.MockUserData
import com.example.domain.old.model.Profile
import com.example.domain.old.model.ShortInfoUser
import com.example.domain.old.repository.UserRepository

internal class UserRepositoryImpl(private val mock : MockUserData) :
    UserRepository {
    override fun getShortInfoUser(): ShortInfoUser {
        return mock.mockShortInfoUser()
    }

    override fun getInfoUserProfile(): Profile {
        return mock.mockInfoUserProfile()
    }
}