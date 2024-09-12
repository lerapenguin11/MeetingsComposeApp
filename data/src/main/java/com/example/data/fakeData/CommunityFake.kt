package com.example.data.fakeData

import com.example.data.responseModel.communiryDetails.CommunityDetailsResponse
import com.example.data.responseModel.community.Category
import com.example.data.responseModel.community.CommunitiesResponse
import com.example.data.responseModel.community.CommunitiesResponseItem
import com.example.data.responseModel.community.Data
import com.example.data.responseModel.community.Members
import kotlin.random.Random

fun communities(): CommunitiesResponse {
    val communitiesResponse = CommunitiesResponse()

    for (i in 1..30) {
        val categories = listOf(
            Category(1 + (i - 1) * 2, "Category ${1 + (i - 1) * 2}"),
            Category(2 + (i - 1) * 2, "Category ${2 + (i - 1) * 2}")
        )
        val community = CommunitiesResponseItem(
            categories = categories,
            description = "A community for category ${categories[0].title} and ${categories[1].title}.",
            id = i,
            image = "https://example.com/community$i.jpg",
            members = Members(
                data = listOf(
                    Data(1 + (i - 1) * 2, "https://example.com/member${1 + (i - 1) * 2}.jpg"),
                    Data(2 + (i - 1) * 2, "https://example.com/member${2 + (i - 1) * 2}.jpg")
                ),
                total = 50 + i * 5
            ),
            title = "Community $i"
        )
        communitiesResponse.add(community)
    }
    return communitiesResponse
}

fun generateCommunityDetails(id: Int): CommunityDetailsResponse {
    val categories = listOf(
        com.example.data.responseModel.communiryDetails.Category(1, "Music"),
        com.example.data.responseModel.communiryDetails.Category(2, "Gaming"),
        com.example.data.responseModel.communiryDetails.Category(3, "Art"),
        com.example.data.responseModel.communiryDetails.Category(4, "Photography"),
        com.example.data.responseModel.communiryDetails.Category(5, "Sports"),
        com.example.data.responseModel.communiryDetails.Category(6, "Technology"),
        com.example.data.responseModel.communiryDetails.Category(7, "Travel"),
        com.example.data.responseModel.communiryDetails.Category(8, "Food"),
        com.example.data.responseModel.communiryDetails.Category(9, "Literature")
    ).shuffled().take(Random.nextInt(1, 4))

    val members = com.example.data.responseModel.communiryDetails.Members(
        `data` = (1..Random.nextInt(10, 100)).map {
            com.example.data.responseModel.communiryDetails.Data(
                it,
                "https://picsum.photos/id/$it/200/300"
            )
        },
        total = Random.nextInt(100, 1000)
    )

    return CommunityDetailsResponse(
        id = id,
        categories = categories,
        description = "This is a description for community $id.",
        image = "https://picsum.photos/id/${id % 100}/400/300",
        isJoined = Random.nextBoolean(),
        members = members,
        title = "Community $id"
    )
}