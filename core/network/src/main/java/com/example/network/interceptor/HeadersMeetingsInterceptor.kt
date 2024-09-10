package com.example.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeadersMeetingsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}