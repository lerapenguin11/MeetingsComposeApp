package com.example.model.community

data class Community(
    val id: Int,
    val title: String,
    val avatarUrl: String?,
    val statusSubscription: Boolean
)