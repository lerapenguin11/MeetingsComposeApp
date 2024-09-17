package com.example.data.fakeData

import kotlin.random.Random

fun generateImageLinks(count: Int): List<String> {
    val baseUrls = listOf(
        "https://picsum.photos/",
        "https://unsplash.com/s/photos/",
        "https://source.unsplash.com/",
        "https://loremflickr.com/640/480/"
    )

    val imageLinks = mutableListOf<String>()

    for (i in 0..count) {
        val baseUrl = baseUrls.random()
        val dimensions = when (baseUrl) {
            "https://picsum.photos/" -> "${Random.nextInt(300, 1000)}/${Random.nextInt(300, 1000)}"
            "https://unsplash.com/s/photos/",
            "https://source.unsplash.com/" -> ""

            else -> "${Random.nextInt(300, 1000)}/${Random.nextInt(300, 1000)}/${
                Random.nextInt(
                    1,
                    10
                )
            }"
        }

        imageLinks.add("$baseUrl$dimensions")
    }

    return imageLinks
}