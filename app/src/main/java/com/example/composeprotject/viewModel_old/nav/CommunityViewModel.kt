package com.example.composeprotject.viewModel_old.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.old.model.Community
import com.example.domain.old.usecase.community.GetCommunitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CommunityViewModel(private val getCommunitiesUseCase: GetCommunitiesUseCase) : ViewModel() {

    private val _communities = MutableStateFlow<List<Community>>(emptyList())
    private val communities: StateFlow<List<Community>> = _communities

    init {
        getCommunityList()
    }

    fun getCommunitiesFlow() = communities

    private fun getCommunityList() {
        getCommunitiesUseCase.execute()
            .onEach { communities ->
                _communities.update { communities }
            }
            .launchIn(viewModelScope)
    }
}