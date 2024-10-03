package com.example.composeprotject.viewModel

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.ext.SdkExtensions
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeprotject.screen.state.EditState
import com.example.domain.model.editUser.EditUserInfo
import com.example.domain.model.token.AutToken
import com.example.domain.model.user.SocialNetwork
import com.example.domain.usecase.editUser.GetPathFromGalleryUriUseCase
import com.example.domain.usecase.editUser.InteractorLoadUserInfoForEdit
import com.example.domain.usecase.getData.GetUserInfoForEdit
import com.example.domain.usecase.store.token.ReadAuthTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class EditUserViewModel(
    private val getPathFromGalleryUriUseCase: GetPathFromGalleryUriUseCase,
    private val readAuthTokenUseCase: ReadAuthTokenUseCase,
    private val interactorLoadUserInfoForEdit: InteractorLoadUserInfoForEdit,
    private val getUserInfoForEdit: GetUserInfoForEdit
) : ViewModel() {
    private val _pathFromGalleryUri = MutableStateFlow<String?>(null)
    private val pathFromGalleryUri: StateFlow<String?> = _pathFromGalleryUri

    private val _editPhoto = MutableStateFlow<String?>(null)
    private val editPhoto: StateFlow<String?> = _editPhoto

    private val _editProfileScreenState = MutableStateFlow(value = EditState.EDIT_PROFILE)
    private val editProfileScreenState: StateFlow<EditState> = _editProfileScreenState

    private val _userInfo = MutableStateFlow<EditUserInfo?>(null)
    private val userInfo: StateFlow<EditUserInfo?> = _userInfo

    init {
        getUserInfoForEdit.execute()
            .onEach {
                _userInfo.emit(value = it)
            }
            .launchIn(scope = viewModelScope)
    }

    fun getPathFromGalleryUriFlow() = pathFromGalleryUri
    fun getEditPhotoFlow() = editPhoto
    fun getEditProfileScreenStateFlow() = editProfileScreenState
    fun getUserInfoFlow() = userInfo

    fun updateUserFullName(fullName: String) {
        _userInfo.update { info -> info?.let { it.copy(fullName = fullName) } }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _userInfo.update { info -> info?.let { it.copy(phoneNumber = phoneNumber) } }
    }

    fun updateBio(bio: String) {
        _userInfo.update { info -> info?.let { it.copy(bio = bio) } }
    }

    fun updateCity(city: String) {
        _userInfo.update { info -> info?.let { it.copy(city = city) } }
    }

    fun updateEditProfileScreenState(state: EditState) {
        _editProfileScreenState.update { state }
    }

    fun updateSocialNetworkHabr(id: String) {
        _userInfo.update { info ->
            info?.let {
                val updatedSocialNetwork = SocialNetwork(
                    habr = id,
                    telegram = it.socialNetwork.telegram
                )
                it.copy(socialNetwork = updatedSocialNetwork)
            }
        }
    }

    fun updateSocialNetworkTelegram(id: String) {
        _userInfo.update { info ->
            info?.let {
                val updatedSocialNetwork = SocialNetwork(
                    habr = it.socialNetwork.habr,
                    telegram = id
                )
                it.copy(socialNetwork = updatedSocialNetwork)
            }
        }
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

    fun loadUserInfoForEdit() {
        readAuthTokenUseCase.execute()
            .onEach { token ->
                token?.let {
                    interactorLoadUserInfoForEdit.execute(token = AutToken(token = it))
                }
            }.launchIn(viewModelScope)
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