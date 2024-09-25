package com.example.domain.usecase.combineUseCase

import com.example.domain.usecase.interest.GetUserInterestsUseCase
import com.example.domain.usecase.store.ReadAuthTokenUseCase
import com.example.domain.usecase.store.ReadUserCityUseCase
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InteractorFullQueryParamLocal : KoinComponent {
    private val readUserCityUseCase: ReadUserCityUseCase by inject()
    private val readAuthTokenUseCase: ReadAuthTokenUseCase by inject()
    private val readUserInterest: GetUserInterestsUseCase by inject()

    private val fullQueryParamLocalFlow = combine(
        readUserInterest.execute(),
        readAuthTokenUseCase.execute(),
        readUserCityUseCase.execute()
    ) { userInterests, authToken, city ->
        CombineFullQueryParamLocal(
            userInterests = userInterests,
            authToken = authToken,
            city = city
        )
    }

    fun execute() = fullQueryParamLocalFlow
}

data class CombineFullQueryParamLocal(
    val userInterests: List<Int>,
    val authToken: String?,
    val city: String?
)