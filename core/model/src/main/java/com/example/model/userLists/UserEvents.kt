package com.example.model.userLists

data class UserEvents(
    val id: Int,
    val categories: List<com.example.model.interest.Category>,
    val avatarUrl: String?,
    val shortAddress: String,
    val startDate: Long,
    val title: String
)
