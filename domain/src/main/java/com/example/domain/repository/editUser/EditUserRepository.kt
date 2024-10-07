package com.example.domain.repository.editUser

import android.net.Uri
import com.example.domain.model.editUser.EditUserInfo
import com.example.domain.model.token.AutToken
import kotlinx.coroutines.flow.Flow


interface EditUserRepository {
    fun getPathFromGalleryUri(uri: Uri): Flow<String?>

    fun getUserInfoForEdit(token: AutToken): Flow<EditUserInfo>
}