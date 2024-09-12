package com.example.domain.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetPeopleUseCase {
    private val streamPeopleWithEventId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private var eventIdLastValue: Int? = null

    private val streamPeopleWithCommunityId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private var communityIdLastValue: Int? = null

    fun loadPeopleByEventId(eventId: Int) {
        eventIdLastValue = eventId
        streamPeopleWithEventId.tryEmit(eventId)
    }

    fun loadPeopleByCommunityId(communityId: Int) {
        communityIdLastValue = communityId
        streamPeopleWithCommunityId.tryEmit(communityId)
    }

    fun eventIdTrigger(): SharedFlow<Int?> = streamPeopleWithEventId

    fun communityIdTrigger(): SharedFlow<Int?> = streamPeopleWithCommunityId
}

class InteractorLoadPeopleByEventId : KoinComponent {
    private val innerPeople: GetPeopleUseCase by inject()

    fun execute(eventId: Int) = innerPeople.loadPeopleByEventId(eventId = eventId)
}

class InteractorLoadPeopleByCategoryId : KoinComponent {
    private val innerPeople: GetPeopleUseCase by inject()

    fun execute(communityId: Int) = innerPeople.loadPeopleByCommunityId(communityId = communityId)
}