package com.example.data.repository

import com.example.domain.model.interest.Interest
import com.example.domain.model.user.SocialNetwork
import com.example.domain.model.user.UserInfo
import com.example.domain.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl : UserRepository {
    override fun getUserInfo(token: String): Flow<UserInfo> {
        return flow {
            emit(value = mockUserInfo())
        }.flowOn(Dispatchers.IO)
    }

    private fun mockUserInfo(): UserInfo { //TODO delete
        return UserInfo(
            avatarUrl = "https://i.pinimg.com/originals/8c/02/b4/8c02b4293087c1fe27641e068c8f624d.jpg",
            fullName = "Сергей",
            bio = "Занимаюсь разрабокой интерфейсов в eCom. Учу HTML, CSS и JavaScript",
            city = "Москва",
            interests = listOf(
                Interest(id = 0, "Разработка"),
                Interest(id = 1, "Продакт менеджмент")
            ),
            socialNetwork = SocialNetwork(
                habr = null,
                telegram = null
            )
        )
    }
}