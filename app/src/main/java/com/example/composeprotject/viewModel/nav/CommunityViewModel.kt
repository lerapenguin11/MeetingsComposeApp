package com.example.composeprotject.viewModel.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.domain.model.nav.Community
import com.example.composeprotject.domain.usecase.community.GetCommunitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel(private val getCommunitiesUseCase: GetCommunitiesUseCase) : ViewModel() {

    private val _communities = MutableStateFlow<List<Community>>(emptyList())
    private val communities : StateFlow<List<Community>> = _communities

    fun getCommunitiesFlow() = communities

    init {
        getCommunityList()
    }

    private fun getCommunityList() = viewModelScope.launch {
        _communities.emit(value = getCommunitiesUseCase())
    }
}