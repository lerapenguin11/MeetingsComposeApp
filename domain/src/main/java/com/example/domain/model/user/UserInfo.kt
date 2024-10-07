package com.example.domain.model.user

import com.example.domain.model.interest.Interest

data class UserInfo(
    val avatarUrl: String?,
    val fullName: String,
    val city: String?,
    val bio: String?,
    val interests: List<Interest>?,
    val socialNetwork: SocialNetwork
)
