package com.example.data.repository

import com.example.domain.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl : UserRepository {
    override fun getUserInfo(token: String): Flow<com.example.model.user.UserInfo> {
        return flow {
            emit(value = mockUserInfo())
        }.flowOn(Dispatchers.IO)
    }

    private fun mockUserInfo(): com.example.model.user.UserInfo { //TODO delete
        return com.example.model.user.UserInfo(
            avatarUrl = "https://i.pinimg.com/originals/8c/02/b4/8c02b4293087c1fe27641e068c8f624d.jpg",
            fullName = "Сергей",
            bio = "Занимаюсь разрабокой интерфейсов в eCom. Учу HTML, CSS и JavaScript",
            city = "Москва",
            interests = listOf(
                com.example.model.interest.Interest(id = 0, "Разработка"),
                com.example.model.interest.Interest(id = 1, "Продакт менеджмент")
            ),
            socialNetwork = com.example.model.user.SocialNetwork(
                habr = null,
                telegram = null
            )
        )
    }
}