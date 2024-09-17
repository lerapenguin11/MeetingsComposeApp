package com.example.data.mappers

import com.example.domain.model.community.Community
import com.example.domain.model.communityDetails.Category
import com.example.domain.model.communityDetails.CommunityDetails
import com.example.domain.model.communityDetails.Data
import com.example.domain.model.communityDetails.Members

class CommunityMapper {

    fun communitiesResponseToCommunities(communitiesResponseItem: com.example.network.responseModel.community.CommunitiesResponseItem): Community {
        return Community(
            id = communitiesResponseItem.id,
            title = communitiesResponseItem.title,
            avatarUrl = communitiesResponseItem.image
        )
    }

    fun communityDetailsResponseToCommunityResponse(response: com.example.network.responseModel.communiryDetails.CommunityDetailsResponse): CommunityDetails {
        return CommunityDetails(
            id = response.id,
            image = response.image,
            title = response.title,
            description = response.description,
            categories = response.categories.map { Category(id = it.id, title = it.title) },
            isJoined = response.isJoined,
            members = Members(
                total = response.members.total,
                data = response.members.data.map { Data(id = it.id, image = it.image) }
            )
        )
    }
}