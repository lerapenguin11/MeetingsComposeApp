package com.example.composeprotject.data.mock

import com.example.domain.model.Community

class MockCommunityData {

    fun communityListMock() = listOf<com.example.domain.model.Community>(
        com.example.domain.model.Community(
            id = 0,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        ),
        com.example.domain.model.Community(
            id = 1,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        ),
        com.example.domain.model.Community(
            id = 2,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        ),
        com.example.domain.model.Community(
            id = 3,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        )
    )
}