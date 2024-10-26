package com.example.data.mappers

import com.example.database.entity.UserInterestEntity

internal class InterestsMapper {

    fun responseInterestToInterest(interestItem: com.example.network.responseModel.interest.InterestItem): com.example.model.interest.Interest {
        return com.example.model.interest.Interest(
            id = interestItem.id,
            title = interestItem.title
        )
    }

    fun interestToEntity(interest: com.example.model.interest.Interest): UserInterestEntity {
        return UserInterestEntity(
            id = interest.id,
            title = interest.title
        )
    }

    fun userInterestEntityToIdInterest(entity: UserInterestEntity): Int {
        return entity.id
    }
}