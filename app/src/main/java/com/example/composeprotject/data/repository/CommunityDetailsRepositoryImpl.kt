package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockCommunityDetails
import com.example.domain.model.CommunityDetails
import com.example.domain.repository.CommunityDetailsRepository

class CommunityDetailsRepositoryImpl(private val mock: MockCommunityDetails) :
    com.example.domain.repository.CommunityDetailsRepository {

    override fun getCommunityById(communityId: Int): com.example.domain.model.CommunityDetails {
        return mock.communityDetails().last { communityId == it.communityId } //TODO
    }
}