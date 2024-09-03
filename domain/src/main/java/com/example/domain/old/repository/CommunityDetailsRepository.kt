package com.example.domain.old.repository

import com.example.domain.old.model.CommunityDetails

interface CommunityDetailsRepository {

    fun getCommunityById(communityId : Int) : CommunityDetails
}