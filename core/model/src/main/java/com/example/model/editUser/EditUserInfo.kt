package com.example.model.editUser

data class EditUserInfo(
    val avatarUrl: String?,
    val fullName: String,
    val phoneNumber: String,
    val city: String?,
    val bio: String?,
    val interests: List<com.example.model.interest.Interest>?,
    val socialNetwork: com.example.model.user.SocialNetwork
)
