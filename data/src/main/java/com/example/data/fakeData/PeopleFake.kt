package com.example.data.fakeData

import com.example.network.responseModel.people.Interest
import com.example.network.responseModel.people.PeopleResponse
import kotlin.random.Random

fun generatePeopleList(count: Int): List<PeopleResponse> {
    val interests = listOf(
        Interest(1, "Музыка"),
        Interest(2, "Кино"),
        Interest(3, "Спорт"),
        Interest(4, "Чтение"),
        Interest(5, "Путешествия"),
        Interest(6, "Искусство"),
        Interest(7, "Технологии"),
        Interest(8, "Природа")
    )

    return (1..count).map { i ->
        PeopleResponse(
            id = i,
            image = "https://mtdata.ru/u3/photoD852/20501229401-0/original.jpg",
            name = "Имя ${i}",
            interests = interests.shuffled().take(Random.nextInt(1, 4))
        )
    }
}