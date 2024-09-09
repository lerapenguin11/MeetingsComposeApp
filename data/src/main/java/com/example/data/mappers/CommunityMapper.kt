package com.example.data.mappers

import com.example.data.responseModel.community.CommunitiesResponseItem
import com.example.domain.model.community.Community

class CommunityMapper {

    fun communitiesResponseToCommunities(communitiesResponseItem: CommunitiesResponseItem): Community {
        return Community(
            id = communitiesResponseItem.id,
            title = communitiesResponseItem.title,
            avatarUrl = communitiesResponseItem.image
        )
    }
}