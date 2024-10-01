package com.example.domain.usecase.editUser

import android.net.Uri
import com.example.domain.repository.editUser.EditUserRepository
import kotlinx.coroutines.flow.Flow

interface GetPathFromGalleryUriUseCase {
    fun execute(uri: Uri): Flow<String?>
}

internal class GetPathFromGalleryUriUseCaseImpl(private val repository: EditUserRepository) :
    GetPathFromGalleryUriUseCase {
    override fun execute(uri: Uri): Flow<String?> {
        return repository.getPathFromGalleryUri(uri = uri)
    }
}