package com.example.data.mappers

import com.example.data.responseModel.interest.InterestItem
import com.example.domain.model.interest.Interest

internal class InterestsMapper {

    fun responseInterestToInterest(interestItem: InterestItem): Interest {
        return Interest(
            id = interestItem.id,
            title = interestItem.title
        )
    }
}