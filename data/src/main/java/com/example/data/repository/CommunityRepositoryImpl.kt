package com.example.data.repository

import com.example.data.mock.MockCommunityData
import com.example.domain.model.Community
import com.example.domain.repository.CommunityRepository

internal class CommunityRepositoryImpl(private val mock: MockCommunityData) :
    CommunityRepository {

    override fun getCommunities(): List<Community> {
        return mock.communityListMock()
    }
}