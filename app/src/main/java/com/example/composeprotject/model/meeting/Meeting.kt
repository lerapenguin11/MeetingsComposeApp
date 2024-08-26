package com.example.composeprotject.model.meeting

data class Meeting(
    val id: Int,
    val categories: List<Category>,
    val avatarUrl: String?,
    val shortAddress: String,
    val startDate: Long,
    val title: String
)

data class Category(
    val id: Int,
    val title: String
)
