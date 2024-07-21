package com.example.data.repository

import com.example.data.mock.MockCommunityDetails
import com.example.domain.model.CommunityDetails
import com.example.domain.repository.CommunityDetailsRepository

internal class CommunityDetailsRepositoryImpl(private val mock: MockCommunityDetails) :
    CommunityDetailsRepository {

    override fun getCommunityById(communityId: Int): CommunityDetails {
        return mock.communityDetails().last { communityId == it.communityId } //TODO
    }
}