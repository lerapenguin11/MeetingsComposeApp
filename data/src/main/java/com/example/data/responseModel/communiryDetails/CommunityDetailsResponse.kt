package com.example.data.responseModel.communiryDetails

data class CommunityDetailsResponse(
    val categories: List<Category>,
    val description: String,
    val id: Int,
    val image: String,
    val isJoined: Boolean,
    val members: Members,
    val title: String
)