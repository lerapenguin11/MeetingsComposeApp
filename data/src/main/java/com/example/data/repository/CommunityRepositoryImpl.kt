package com.example.data.repository

import com.example.data.mock.MockCommunityData
import com.example.domain.model.Community
import com.example.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow

internal class CommunityRepositoryImpl(private val mock: MockCommunityData) :
    CommunityRepository {

    override fun getCommunities(): List<Community> {
        return mock.communityListMock()
    }
}