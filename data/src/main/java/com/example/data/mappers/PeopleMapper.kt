package com.example.data.mappers

import com.example.data.responseModel.people.PeopleResponse
import com.example.domain.model.interest.Interest
import com.example.domain.model.people.People

class PeopleMapper {

    fun peopleResponseToPeople(peopleResponse: PeopleResponse): People {
        return People(
            id = peopleResponse.id,
            name = peopleResponse.name,
            image = peopleResponse.image,
            interests = peopleResponse.interests.map { Interest(id = it.id, title = it.title) }
        )
    }
}