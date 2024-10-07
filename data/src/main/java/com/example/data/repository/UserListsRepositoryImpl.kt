package com.example.data.repository

import com.example.domain.model.interest.Category
import com.example.domain.model.userLists.UserCommunities
import com.example.domain.model.userLists.UserEvents
import com.example.domain.repository.userLists.UserListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListsRepositoryImpl : UserListsRepository {
    override fun getUserEvents(): Flow<List<UserEvents>> {
        return flow {
            emit(value = mockUserEvents())
        }.flowOn(Dispatchers.IO)
    }

    override fun getUserCommunities(): Flow<List<UserCommunities>> {
        return flow {
            emit(value = mockUserCommunities())
        }
    }

    private fun mockUserEvents(): List<UserEvents> {
        return listOf(
            UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(Category(id = 0, title = "Разработка"))
            ),
            UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(Category(id = 0, title = "Разработка"))
            ),
            UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(Category(id = 0, title = "Разработка"))
            ),
            UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(Category(id = 0, title = "Разработка"))
            ),
            UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(Category(id = 0, title = "Разработка"))
            )
        )
    }

    private fun mockUserCommunities(): List<UserCommunities> {
        return listOf(
            UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            )
        )
    }
}