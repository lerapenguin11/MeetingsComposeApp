package com.example.domain.repository

import com.example.domain.model.Community

interface CommunityRepository {

    fun getCommunities() : List<Community>
}