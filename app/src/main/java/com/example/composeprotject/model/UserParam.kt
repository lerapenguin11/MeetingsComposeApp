package com.example.composeprotject.model

import com.example.domain.model.interest.Interest

data class UserParam(
    val eventId: Int,
    val name: String?,
    val phoneNumber: String?,
    val userInterests: List<Interest>?
)