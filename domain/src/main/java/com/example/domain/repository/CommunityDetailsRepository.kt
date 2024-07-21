package com.example.domain.repository

import com.example.domain.model.CommunityDetails

interface CommunityDetailsRepository {

    fun getCommunityById(communityId : Int) : CommunityDetails
}