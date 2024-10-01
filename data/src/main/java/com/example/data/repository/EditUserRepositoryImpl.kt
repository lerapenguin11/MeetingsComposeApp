package com.example.data.repository

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.domain.repository.editUser.EditUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
}