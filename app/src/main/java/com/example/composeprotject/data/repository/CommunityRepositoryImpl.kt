package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockCommunityData
import com.example.composeprotject.domain.model.nav.Community
import com.example.composeprotject.domain.repository.CommunityRepository

class CommunityRepositoryImpl(private val mock: MockCommunityData) : CommunityRepository {

    override fun getCommunities(): List<Community> {
        return mock.communityListMock()
    }
}