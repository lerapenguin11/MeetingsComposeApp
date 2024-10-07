package com.example.domain.usecase.getData

import com.example.domain.model.people.People
import com.example.domain.repository.people.PeopleRepository
import com.example.domain.usecase.people.GetPeopleUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.merge
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPeople : KoinComponent {
    private val innerPeople: GetPeopleUseCase by inject()
    private val repository: PeopleRepository by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val peoplePrepared: Flow<List<People>> =
        merge(
            innerPeople.eventIdTrigger().filterNotNull().mapLatest {
                repository.getPeopleByEventId(eventId = it)
            },
            innerPeople.communityIdTrigger().filterNotNull().mapLatest {
                repository.getPeopleByCommunityId(communityId = it)
            }
        ).flattenConcat()

    fun execute() = peoplePrepared
}