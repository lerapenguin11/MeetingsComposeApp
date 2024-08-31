package com.example.composeprotject.ui.component.utils

import android.content.Context
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers

fun imageCash(context: Context, imageUrl: String?): ImageRequest {
    val listener = object : ImageRequest.Listener {

    }
    return ImageRequest.Builder(context = context)
        .data(imageUrl)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
}