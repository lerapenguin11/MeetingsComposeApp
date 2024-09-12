package com.example.domain.model.people

import com.example.domain.model.interest.Interest

data class People(
    val id: Int,
    val image: String?,
    val name: String,
    val interests: List<Interest>
)
