package com.example.domain.stubs

import com.example.domain.model.Community

internal class CommunitiesStubs {

    fun communities() = MutableList(10) {
        Community(
            id = 0,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        )
    }
}