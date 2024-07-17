package com.example.composeprotject.domain.repository

import com.example.composeprotject.domain.model.Community

interface CommunityRepository {

    fun getCommunities() : List<Community>
}