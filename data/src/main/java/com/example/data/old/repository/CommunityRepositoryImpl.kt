package com.example.data.old.repository

import com.example.data.old.mock.MockCommunityData
import com.example.domain.old.model.Community
import com.example.domain.old.repository.CommunityRepository

internal class CommunityRepositoryImpl(private val mock: MockCommunityData) :
    CommunityRepository {

    override fun getCommunities(): List<Community> {
        return mock.communityListMock()
    }
}