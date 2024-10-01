package com.example.domain.repository.editUser

import android.net.Uri
import kotlinx.coroutines.flow.Flow


interface EditUserRepository {
    fun getPathFromGalleryUri(uri: Uri): Flow<String?>
}