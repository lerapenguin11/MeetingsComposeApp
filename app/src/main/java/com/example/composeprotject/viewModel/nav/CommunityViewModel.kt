package com.example.composeprotject.viewModel.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Community
import com.example.domain.usecase.community.GetCommunitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel(private val getCommunitiesUseCase: GetCommunitiesUseCase) : ViewModel() {

    private val _communities = MutableStateFlow<List<Community>>(emptyList())
    private val communities : StateFlow<List<Community>> = _communities

    init {
        getCommunityList()
    }

    fun getCommunitiesFlow() = communities

    private fun getCommunityList() = viewModelScope.launch {
        _communities.emit(value = getCommunitiesUseCase.execute())
    }
}