package com.example.domain.stubs

import com.example.domain.model.Profile
import com.example.domain.model.ShortInfoUser

internal class UserStubs {

    fun profile() = Profile(
        userName = "Ivan",
        userSurname = "Ivanov",
        avatarUrl = null,
        phoneNumber = "+7 (989) 811-22-11",
        twitterId = "",
        instagramId = "",
        facebookId = "",
        linkedinId = ""
    )

    fun shortInfoUser() = ShortInfoUser(
        userName = "Ivan",
        userSurname = "Ivanov",
        avatarUrl = null,
        phoneNumber = "+7 (989) 811-22-11"
    )
}