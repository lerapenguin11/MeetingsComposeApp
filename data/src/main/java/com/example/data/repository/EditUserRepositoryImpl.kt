package com.example.data.repository

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.domain.model.editUser.EditUserInfo
import com.example.domain.model.interest.Interest
import com.example.domain.model.token.AutToken
import com.example.domain.model.user.SocialNetwork
import com.example.domain.repository.editUser.EditUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EditUserRepositoryImpl(private val context: Context) : EditUserRepository {
    override fun getPathFromGalleryUri(uri: Uri): Flow<String?> {
        return flow {
            val projection = arrayOf(MediaStore.Images.Media.DATA)

            val cursor = context.contentResolver.query(uri, projection, null, null, null)

            cursor?.moveToFirst()

            val columnIndex = cursor?.getColumnIndex(projection[0])

            val path = columnIndex?.let { cursor.getString(it) }

            cursor?.close()

            emit(value = path)
        }
    }

    override fun getUserInfoForEdit(token: AutToken): Flow<EditUserInfo> {
        return flow {
            val response = userInfoForEditMock() //TODO запрос на сервер

            emit(value = response)
        }.flowOn(Dispatchers.IO)
    }

    private fun userInfoForEditMock(): EditUserInfo {
        return EditUserInfo(
            avatarUrl = "https://i.pinimg.com/originals/8c/02/b4/8c02b4293087c1fe27641e068c8f624d.jpg",
            fullName = "Сергей",
            bio = "Занимаюсь разрабокой интерфейсов в eCom. Учу HTML, CSS и JavaScript",
            city = "Москва",
            interests = listOf(
                Interest(id = 0, "Разработка"),
                Interest(id = 1, "Продакт менеджмент")
            ),
            socialNetwork = SocialNetwork(
                habr = "SergeyPyaterkin",
                telegram = "serega1998"
            ),
            phoneNumber = "79961757814"
        )
    }
}