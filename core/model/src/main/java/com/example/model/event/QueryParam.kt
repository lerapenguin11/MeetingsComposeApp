package com.example.model.event

data class QueryParam(
    val userInterests: List<Int>?,
    val authToken: String?,
    val city: String?
)