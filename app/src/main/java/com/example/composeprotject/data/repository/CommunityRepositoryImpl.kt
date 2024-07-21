package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockCommunityData
import com.example.domain.model.Community
import com.example.domain.repository.CommunityRepository

class CommunityRepositoryImpl(private val mock: MockCommunityData) :
    com.example.domain.repository.CommunityRepository {

    override fun getCommunities(): List<com.example.domain.model.Community> {
        return mock.communityListMock()
    }
}