package com.example.domain.model.community

data class Community(
    val id: Int,
    val title: String,
    val avatarUrl: String?,
    val statusSubscription: Boolean
)