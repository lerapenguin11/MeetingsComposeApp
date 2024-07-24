package com.example.data.mock

import com.example.domain.model.Community

class MockCommunityData {

    fun communityListMock() = MutableList(10) {
        Community(
            id = 0,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        )
    }
}