package com.example.data.mappers

import android.text.TextUtils
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class CommunityMapper {

    fun communitiesResponseToCommunities(communitiesResponseItem: com.example.network.responseModel.community.CommunitiesResponseItem): com.example.model.community.Community {
        return com.example.model.community.Community(
            id = communitiesResponseItem.id,
            title = communitiesResponseItem.title,
            avatarUrl = communitiesResponseItem.image,
            statusSubscription = false //TODO
        )
    }

    fun communityDetailsResponseToCommunityResponse(response: com.example.network.responseModel.communiryDetails.CommunityDetailsResponse): com.example.model.communityDetails.CommunityDetails {
        return com.example.model.communityDetails.CommunityDetails(
            id = response.id,
            image = response.image,
            title = response.title,
            description = response.description,
            categories = response.categories.map {
                com.example.model.communityDetails.Category(
                    id = it.id,
                    title = it.title
                )
            },
            isJoined = response.isJoined,
            members = com.example.model.communityDetails.Members(
                total = response.members.total,
                data = response.members.data.map {
                    com.example.model.communityDetails.Data(
                        id = it.id,
                        image = it.image
                    )
                }
            )
        )
    }

    fun typeConvectorListIdToUriId(ids: List<Int>): String? {
        return try {
            URLEncoder.encode(TextUtils.join(",", ids), "utf-8")
        } catch (e: UnsupportedEncodingException) {
            null
        }
    }
}