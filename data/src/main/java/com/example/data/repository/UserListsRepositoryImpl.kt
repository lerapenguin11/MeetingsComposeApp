package com.example.data.repository

import com.example.domain.repository.userLists.UserListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListsRepositoryImpl : UserListsRepository {
    override fun getUserEvents(): Flow<List<com.example.model.userLists.UserEvents>> {
        return flow {
            emit(value = mockUserEvents())
        }.flowOn(Dispatchers.IO)
    }

    override fun getUserCommunities(): Flow<List<com.example.model.userLists.UserCommunities>> {
        return flow {
            emit(value = mockUserCommunities())
        }
    }

    private fun mockUserEvents(): List<com.example.model.userLists.UserEvents> {
        return listOf(
            com.example.model.userLists.UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(
                    com.example.model.interest.Category(
                        id = 0,
                        title = "Разработка"
                    )
                )
            ),
            com.example.model.userLists.UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(
                    com.example.model.interest.Category(
                        id = 0,
                        title = "Разработка"
                    )
                )
            ),
            com.example.model.userLists.UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(
                    com.example.model.interest.Category(
                        id = 0,
                        title = "Разработка"
                    )
                )
            ),
            com.example.model.userLists.UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(
                    com.example.model.interest.Category(
                        id = 0,
                        title = "Разработка"
                    )
                )
            ),
            com.example.model.userLists.UserEvents(
                id = 0,
                title = "Андроидкор QA 2024",
                startDate = 1727325001,
                shortAddress = "Большая Конюшенная, 10",
                avatarUrl = null,
                categories = listOf(
                    com.example.model.interest.Category(
                        id = 0,
                        title = "Разработка"
                    )
                )
            )
        )
    }

    private fun mockUserCommunities(): List<com.example.model.userLists.UserCommunities> {
        return listOf(
            com.example.model.userLists.UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            com.example.model.userLists.UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            com.example.model.userLists.UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            com.example.model.userLists.UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            com.example.model.userLists.UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            ),
            com.example.model.userLists.UserCommunities(
                id = 0,
                title = "Хабр",
                avatarUrl = null
            )
        )
    }
}