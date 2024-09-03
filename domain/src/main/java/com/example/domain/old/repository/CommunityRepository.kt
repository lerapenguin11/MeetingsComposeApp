package com.example.domain.old.repository

import com.example.domain.old.model.Community

interface CommunityRepository {

    fun getCommunities() : List<Community>
}