package com.example.composeprotject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.people.People
import com.example.domain.usecase.InteractorLoadPeopleByCategoryId
import com.example.domain.usecase.InteractorLoadPeopleByEventId
import com.example.domain.usecase.getData.GetPeople
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PeopleViewModel(
    private val loadPeopleByEventId: InteractorLoadPeopleByEventId,
    private val loadPeopleByCategoryId: InteractorLoadPeopleByCategoryId,
    private val getPeople: GetPeople
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val people: StateFlow<List<People>> = getPeople.execute()
        .filterNotNull().flatMapLatest {
            flow {
                emit(value = it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun getPeople() = people

    fun loadPeopleByEventId(eventId: Int) = viewModelScope.launch {
        loadPeopleByEventId.execute(eventId = eventId)
    }

    fun loadPeopleByCategoryId(communityId: Int) = viewModelScope.launch {
        loadPeopleByCategoryId.execute(communityId = communityId)
    }
}