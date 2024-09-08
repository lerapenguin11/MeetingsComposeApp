package com.example.domain.model.event

import com.example.domain.model.interest.Category

data class Meeting(
    val id: Int,
    val categories: List<Category>,
    val avatarUrl: String?,
    val shortAddress: String,
    val startDate: Long,
    val title: String
)
