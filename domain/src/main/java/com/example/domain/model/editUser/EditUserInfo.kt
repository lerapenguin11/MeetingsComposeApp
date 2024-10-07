package com.example.domain.model.editUser

import com.example.domain.model.interest.Interest
import com.example.domain.model.user.SocialNetwork

data class EditUserInfo(
    val avatarUrl: String?,
    val fullName: String,
    val phoneNumber: String,
    val city: String?,
    val bio: String?,
    val interests: List<Interest>?,
    val socialNetwork: SocialNetwork
)
