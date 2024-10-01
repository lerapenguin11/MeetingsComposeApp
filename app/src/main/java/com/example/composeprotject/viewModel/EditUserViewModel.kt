package com.example.composeprotject.viewModel

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.ext.SdkExtensions
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.screen.state.EditState
import com.example.domain.usecase.editUser.GetPathFromGalleryUriUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class EditUserViewModel(
    private val getPathFromGalleryUriUseCase: GetPathFromGalleryUriUseCase
) : ViewModel() {
    private val _pathFromGalleryUri = MutableStateFlow<String?>(null)
    private val pathFromGalleryUri: StateFlow<String?> = _pathFromGalleryUri

    private val _editPhoto = MutableStateFlow<String?>(null)
    private val editPhoto: StateFlow<String?> = _editPhoto

    private val _editProfileScreenState = MutableStateFlow(value = EditState.EDIT_PROFILE)
    private val editProfileScreenState: StateFlow<EditState> = _editProfileScreenState

    fun getPathFromGalleryUriFlow() = pathFromGalleryUri
    fun getEditPhotoFlow() = editPhoto
    fun editProfileScreenStateFlow() = editProfileScreenState

    fun updateEditProfileScreenState(state: EditState) {
        _editProfileScreenState.update { state }
    }

    fun updatePhoto(data: String) {
        _editPhoto.update { data }
    }

    fun getPathFromGalleryUri(uri: Uri) {
        getPathFromGalleryUriUseCase.execute(uri = uri)
            .onEach { galleryUri ->
                _pathFromGalleryUri.update { galleryUri }
            }.launchIn(viewModelScope)
    }

    fun getChooseImageIntent(): Intent {
        val galleryIntent = getGalleryIntent()

        val chooserIntent = Intent(Intent.ACTION_CHOOSER).apply {
            putExtra(Intent.EXTRA_INTENT, galleryIntent)
        }

        return chooserIntent
    }

    private fun getGalleryIntent() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.R
            ) >= 2
        ) {
            Intent(MediaStore.ACTION_PICK_IMAGES)
        } else {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        }
}