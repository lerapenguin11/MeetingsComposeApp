package com.example.composeprotject.domain.repository

import com.example.composeprotject.domain.model.CommunityDetails

interface CommunityDetailsRepository {

    fun getCommunityById(communityId : Int) : CommunityDetails
}