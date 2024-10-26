package com.example.data.mappers

class PeopleMapper {

    fun peopleResponseToPeople(peopleResponse: com.example.network.responseModel.people.PeopleResponse): com.example.model.people.People {
        return com.example.model.people.People(
            id = peopleResponse.id,
            name = peopleResponse.name,
            image = peopleResponse.image,
            interests = peopleResponse.interests.map {
                com.example.model.interest.Interest(
                    id = it.id,
                    title = it.title
                )
            }
        )
    }
}