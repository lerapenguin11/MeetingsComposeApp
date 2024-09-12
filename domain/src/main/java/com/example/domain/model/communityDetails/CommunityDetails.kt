package com.example.domain.model.communityDetails

data class CommunityDetails(
    val id: Int,
    val categories: List<Category>,
    val description: String,
    val image: String?,
    val isJoined: Boolean,
    val members: Members,
    val title: String
)
