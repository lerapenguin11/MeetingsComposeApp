package com.example.network.responseModel.people


data class PeopleResponse(
    val id: Int,
    val image: String?,
    val name: String,
    val interests: List<Interest>
)
