package com.example.data.responseModel.community

class CommunitiesResponse : ArrayList<CommunitiesResponseItem>()

data class CommunitiesResponseItem(
    val categories: List<Category>,
    val description: String,
    val id: Int,
    val image: String,
    val members: Members,
    val title: String
)