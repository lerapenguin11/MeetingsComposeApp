package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockUserData
import com.example.composeprotject.domain.model.Profile
import com.example.composeprotject.domain.model.nav.ShortInfoUser
import com.example.composeprotject.domain.repository.UserRepository

class UserRepositoryImpl(private val mock : MockUserData) : UserRepository {

    override fun getShortInfoUser(): ShortInfoUser {
        return mock.mockShortInfoUser()
    }

    override fun getInfoUserProfile(): Profile {
        return mock.mockInfoUserProfile()
    }
}