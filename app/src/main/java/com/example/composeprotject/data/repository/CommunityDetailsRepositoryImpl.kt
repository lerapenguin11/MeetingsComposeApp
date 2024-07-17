package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockCommunityDetails
import com.example.composeprotject.domain.model.CommunityDetails
import com.example.composeprotject.domain.repository.CommunityDetailsRepository

class CommunityDetailsRepositoryImpl(private val mock: MockCommunityDetails) :
    CommunityDetailsRepository {

    override fun getCommunityById(communityId: Int): CommunityDetails {
        return mock.communityDetails().last { communityId == it.communityId } //TODO
    }
}