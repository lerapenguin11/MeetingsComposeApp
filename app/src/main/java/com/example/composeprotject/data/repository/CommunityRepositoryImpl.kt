package com.example.composeprotject.data.repository

import com.example.composeprotject.domain.model.nav.Community
import com.example.composeprotject.domain.repository.CommunityRepository

class CommunityRepositoryImpl : CommunityRepository {

    override fun getCommunities(): List<Community> {
        return communityListMock()
    }

    private fun communityListMock() = listOf<Community>(
        Community(
            id = 0,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        ),
        Community(
            id = 1,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        ),
        Community(
            id = 2,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        ),
        Community(
            id = 3,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        )
    )
}