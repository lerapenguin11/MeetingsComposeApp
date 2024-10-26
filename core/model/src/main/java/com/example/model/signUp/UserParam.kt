package com.example.model.signUp

import com.example.model.interest.Interest

data class UserParam(
    val eventId: Int,
    val name: String,
    val phoneNumber: String,
    val userInterests: List<Interest>?
)