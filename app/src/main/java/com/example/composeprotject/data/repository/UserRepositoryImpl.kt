package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockUserData
import com.example.domain.model.Profile
import com.example.domain.model.ShortInfoUser
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(private val mock : MockUserData) :
    com.example.domain.repository.UserRepository {

    override fun getShortInfoUser(): com.example.domain.model.ShortInfoUser {
        return mock.mockShortInfoUser()
    }

    override fun getInfoUserProfile(): com.example.domain.model.Profile {
        return mock.mockInfoUserProfile()
    }
}