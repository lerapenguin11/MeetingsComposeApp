package com.example.data.mappers

import com.example.database.entity.UserInterestEntity
import com.example.domain.model.interest.Interest

internal class InterestsMapper {

    fun responseInterestToInterest(interestItem: com.example.network.responseModel.interest.InterestItem): Interest {
        return Interest(
            id = interestItem.id,
            title = interestItem.title
        )
    }

    fun interestToEntity(interest: Interest): UserInterestEntity {
        return UserInterestEntity(
            id = interest.id,
            title = interest.title
        )
    }

    fun userInterestEntityToIdInterest(entity: UserInterestEntity): Int {
        return entity.id
    }
}