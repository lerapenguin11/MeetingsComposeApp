package com.example.model.people

data class People(
    val id: Int,
    val image: String?,
    val name: String,
    val interests: List<com.example.model.interest.Interest>
)
