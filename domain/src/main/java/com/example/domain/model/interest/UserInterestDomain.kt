package com.example.domain.model.interest

data class UserInterestDomain(
    val userInterest: List<Interest>,
    val addVariantInterests: AddVariantInterests
)

enum class AddVariantInterests {
    LOCAL,
    REMOTE_AND_LOCAL
}