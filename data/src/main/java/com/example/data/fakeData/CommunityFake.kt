package com.example.data.fakeData

import com.example.data.responseModel.community.Category
import com.example.data.responseModel.community.CommunitiesResponse
import com.example.data.responseModel.community.CommunitiesResponseItem
import com.example.data.responseModel.community.Data
import com.example.data.responseModel.community.Members

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