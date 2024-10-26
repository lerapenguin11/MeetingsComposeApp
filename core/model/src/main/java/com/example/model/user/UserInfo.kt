package com.example.model.user

data class UserInfo(
    val avatarUrl: String?,
    val fullName: String,
    val city: String?,
    val bio: String?,
    val interests: List<com.example.model.interest.Interest>?,
    val socialNetwork: SocialNetwork
)
