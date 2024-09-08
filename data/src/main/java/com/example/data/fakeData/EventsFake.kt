package com.example.data.fakeData

import com.example.data.responseModel.event.Address
import com.example.data.responseModel.event.Category
import com.example.data.responseModel.event.Coordinates
import com.example.data.responseModel.event.EventResponse
import com.example.data.responseModel.event.EventResponseItem
import com.example.data.responseModel.event.Location
import com.example.data.responseModel.event.Plain
import java.util.Calendar
import kotlin.random.Random

fun eventsFake(): EventResponse {
    val eventResponse = EventResponse()
    val categories = listOf(
        Category(0, "Дизайн"),
        Category(1, "Разработка"),
        Category(2, "Продакт менеджмент"),
        Category(3, "Проджект менеджмент"),
        Category(4, "Backend"),
        Category(5, "Frontend"),
        Category(6, "Mobile"),
        Category(7, "Тестирование"),
        Category(8, "Продажи"),
        Category(9, "Бизнес"),
        Category(10, "Безопасность"),
        Category(11, "Web"),
        Category(12, "Девопс"),
        Category(13, "Маркетинг"),
        Category(14, "Аналитика")
    )

    val imageLinks = generateImageLinks(30)

    for (i in 1..30) {
        // Randomly select 2-3 categories for each event
        val selectedCategories = categories.shuffled().take(Random.nextInt(4))

        eventResponse.add(
            EventResponseItem(
                categories = selectedCategories,
                id = i,
                image = imageLinks[i],
                location = Location(
                    address = Address(
                        plain = Plain(
                            full = "Full Address $i",
                            short = "Short Address $i"
                        )
                    ),
                    coordinates = Coordinates(
                        lat = 37.7749 + i * 0.01,
                        lon = -122.4194 + i * 0.01
                    )
                ),
                startDate = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, i)
                }.timeInMillis / 1000, // Unix timestamp
                title = "Event $i"
            )
        )
    }
    return eventResponse
}

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