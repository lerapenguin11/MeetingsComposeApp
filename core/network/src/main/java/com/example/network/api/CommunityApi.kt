package com.example.network.api

import com.example.network.responseModel.community.CommunitiesResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommunityApi {

    @GET("communities")
    suspend fun getCommunity(
        @Query("query") query: String? = null,
        @Query(encoded = true, value = "categories") categories: String? = null,
    ): ApiResponse<CommunitiesResponse>
}