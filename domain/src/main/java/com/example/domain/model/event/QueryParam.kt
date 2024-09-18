package com.example.domain.model.event

data class QueryParam(
    val userInterests: List<Int>?,
    val authToken: String?,
    val city: String?,
    val filteredParam: List<Int>
)