package com.example.composeprotject.data.mock

import com.example.domain.model.Profile
import com.example.domain.model.ShortInfoUser

class MockUserData {
    fun mockInfoUserProfile() = com.example.domain.model.Profile(
        userName = "Ivan",
        userSurname = "Ivanov",
        avatarUrl = null,
        phoneNumber = "+7 (989) 811-22-11",
        twitterId = "",
        instagramId = "",
        facebookId = "",
        linkedinId = ""
    )

    fun mockShortInfoUser() = com.example.domain.model.ShortInfoUser(
        userName = "Ivan",
        userSurname = "Ivanov",
        avatarUrl = null,
        phoneNumber = "+7 (989) 811-22-11"
    )
}