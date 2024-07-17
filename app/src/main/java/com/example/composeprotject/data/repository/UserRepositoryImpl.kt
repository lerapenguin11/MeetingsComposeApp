package com.example.composeprotject.data.repository

import com.example.composeprotject.domain.model.nav.ShortInfoUser
import com.example.composeprotject.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {

    override fun getShortInfoUser(): ShortInfoUser {
        return shortInfoUser()
    }

    private fun shortInfoUser() = ShortInfoUser(
        userName = "Ivan",
        userSurname = "Ivanov",
        avatarUrl = null,
        phoneNumber = "+7 (989) 811-22-11"
    )
}