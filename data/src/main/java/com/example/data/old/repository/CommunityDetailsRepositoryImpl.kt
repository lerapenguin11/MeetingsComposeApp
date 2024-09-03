package com.example.data.old.repository

import com.example.data.old.mock.MockCommunityDetails
import com.example.domain.old.model.CommunityDetails
import com.example.domain.old.repository.CommunityDetailsRepository

internal class CommunityDetailsRepositoryImpl(private val mock: MockCommunityDetails) :
    CommunityDetailsRepository {

    override fun getCommunityById(communityId: Int): CommunityDetails {
        return mock.communityDetails().last { communityId == it.communityId } //TODO
    }
}