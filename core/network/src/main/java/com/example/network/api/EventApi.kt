package com.example.network.api

import com.example.network.responseModel.event.EventResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {

    @GET("meetings")
    suspend fun getMeetings(
        @Query("type") eventType: String? = null,
        @Query(encoded = true, value = "categories") categories: String? = null,
        @Query("query") query: String? = null,
        @Query("city") city: String? = null
    ): ApiResponse<EventResponse>
}