package com.example.model.signUp

data class UserParam(
    val eventId: Int,
    val name: String,
    val phoneNumber: String,
    val userInterests: List<com.example.model.interest.Interest>?
)