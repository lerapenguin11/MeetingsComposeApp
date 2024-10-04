package com.example.composeprotject.screen.profile

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeprotject.R
import com.example.composeprotject.screen.state.EditState
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.EditChip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.input.InputFieldIcon
import com.example.composeprotject.ui.component.input.SimpleInputField
import com.example.composeprotject.ui.component.phoneInput.SimplePhoneInputFields
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.switcher.CustomSwitch
import com.example.composeprotject.ui.component.topBar.standard.TopAppBar
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.EditUserViewModel
import com.example.domain.model.editUser.EditUserInfo
import com.example.domain.model.interest.Interest
import com.example.domain.model.user.SocialNetwork
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    navController: NavController,
    isSaveData: Boolean,
    editUserViewModel: EditUserViewModel = koinViewModel(),
    onGoProfileAfterSaving: (Boolean) -> Unit
) {
    val editPhoto by editUserViewModel.getEditPhotoFlow().collectAsStateWithLifecycle()
    val galleryUri by editUserViewModel.getPathFromGalleryUriFlow().collectAsStateWithLifecycle()
    val editProfileStateScreen by editUserViewModel.getEditProfileScreenStateFlow()
        .collectAsStateWithLifecycle()
    val userInfo by editUserViewModel.getUserInfoFlow().collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        editUserViewModel.loadUserInfoForEdit()
    }

    LaunchedEffect(isSaveData) {
        if (isSaveData) {
            kotlinx.coroutines.delay(5000)
            onGoProfileAfterSaving(true)
        }
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data?.data
                data?.let { it1 -> editUserViewModel.getPathFromGalleryUri(it1) }
                galleryUri?.let { data -> editUserViewModel.updatePhoto(data = data) }
            }
        }

    when (editProfileStateScreen) {
        EditState.EDIT_PROFILE -> {
            EditProfile(
                userInfo = userInfo,
                editUserViewModel = editUserViewModel,
                navController = navController,
                galleryUri = galleryUri,
                launcher = launcher
            )
        }

        EditState.EDIT_PICTURE -> {
            EditPicture(
                currentPhoto = galleryUri ?: userInfo?.avatarUrl,
                galleryUri = galleryUri,
                onCancel = { editUserViewModel.updateEditProfileScreenState(state = EditState.EDIT_PROFILE) },
                onSave = { editUserViewModel.updateEditProfileScreenState(state = EditState.EDIT_PROFILE) },
                onChooseAnotherPhoto = { launcher.launch(editUserViewModel.getChooseImageIntent()) }
            )
        }
    }
}

@Composable
private fun EditPicture(
    currentPhoto: String?,
    galleryUri: String?,
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onChooseAnotherPhoto: () -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(size = MeetTheme.sizes.sizeX10))
                .padding(horizontal = MeetTheme.sizes.sizeX16, vertical = MeetTheme.sizes.sizeX20)
                .clickable {
                    onCancel()
                }
        ) {
            Image(
                modifier = Modifier
                    .size(size = MeetTheme.sizes.sizeX24),
                painter = painterResource(id = CommonDrawables.ic_close_bt),
                contentDescription = null
            )
        }
        Box(
            modifier = with(modifier) {
                fillMaxWidth()
                    .weight(3.5f)
            }
        ) {
            AsyncImage(
                model = imageCash(
                    context = LocalContext.current,
                    imageUrl = galleryUri ?: currentPhoto
                ),
                placeholder = painterResource(id = CommonDrawables.ic_avatar_user_profile),
                error = painterResource(id = CommonDrawables.ic_avatar_user_profile),
                contentDescription = stringResource(CommonString.text_avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(height = 375.dp)
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()
                    drawCircle(
                        color = Color(0xFFFFFFFF),
                        center = Offset(x = size.width / 2, y = size.height / 2),
                        radius = size.height / 2.15f,
                        blendMode = BlendMode.DstOut
                    )
                }
                .background(color = MeetTheme.colors.blackTransparent)
            )
        }
        Column(
            modifier = Modifier
                .weight(2.5f)
                .padding(horizontal = MeetTheme.sizes.sizeX16),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        onChooseAnotherPhoto()
                    },
                text = stringResource(CommonString.text_choose_another_photo),
                color = MeetTheme.colors.darkGray,
                style = MeetTheme.typography.interMedium18
            )
            SpacerHeight(height = 24.dp)
            FilledButton(
                state = FilledButtonState.ACTIVE_PRIMARY,
                buttonText = stringResource(id = CommonString.text_save)
            ) {
                onSave()
            }
            SpacerHeight(height = 28.dp)
        }
    }
}

@Composable
private fun EditProfile(
    userInfo: EditUserInfo?,
    navController: NavController,
    galleryUri: String?,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    editUserViewModel: EditUserViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        userInfo?.let { info ->
            item {
                AvatarBlock(
                    launcher = launcher,
                    galleryUri = galleryUri,
                    editUserViewModel = editUserViewModel,
                    currentPhoto = info.avatarUrl,
                    navController = navController
                )
            }
            item {
                BlockInputUserInformation(
                    fullName = info.fullName,
                    phoneNumber = info.phoneNumber,
                    city = info.city,
                    bio = info.bio,
                    onFullNameChange = {
                        editUserViewModel.updateUserFullName(fullName = it)
                    },
                    onCityChange = {
                        editUserViewModel.updateCity(city = it)
                    },
                    onBioChange = {
                        editUserViewModel.updateBio(bio = it)
                    },
                    onPhoneNumberChange = {
                        editUserViewModel.updatePhoneNumber(phoneNumber = it)
                    }
                )
            }
            item {
                InterestsSelectionBlock(
                    userInterests = info.interests,
                    onClickUserInterest = {
                        //TODO
                    }
                )
            }
            item {
                SocialMediaBlock(
                    socialNetwork = info.socialNetwork,
                    onValueHabrChange = {
                        editUserViewModel.updateSocialNetworkHabr(id = it)
                    },
                    onValueTelegramChange = {
                        editUserViewModel.updateSocialNetworkTelegram(id = it)
                    }
                )
            }
            item {
                NotificationBlock()
            }
            item {
                DeleteProfileBlock()
            }
        }
    }
}

@Composable
private fun DeleteProfileBlock(
    modifier: Modifier = Modifier
) {
    SpacerHeight(height = MeetTheme.sizes.sizeX40)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = MeetTheme.sizes.sizeX16,
                start = MeetTheme.sizes.sizeX16,
                end = MeetTheme.sizes.sizeX16,
                bottom = 28.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Text(
                modifier = Modifier
                    .clickable {
                        //TODO
                    },
                text = stringResource(CommonString.text_delete_profile),
                color = MeetTheme.colors.red,
                style = MeetTheme.typography.interMedium18
            )
        }
    }
}

@Composable
private fun NotificationBlock(
    modifier: Modifier = Modifier
) {
    SpacerHeight(height = MeetTheme.sizes.sizeX40)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        Box(modifier = Modifier.weight(weight = 2.0f)) {
            Text(
                text = stringResource(CommonString.text_show_my_communities),
                color = MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18
            )
        }
        CustomSwitch(
            isSwitchOn = true, //TODO
            onSwitch = {
                //TODO
            }
        )
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        Box(modifier = Modifier.weight(weight = 2.0f)) {
            Text(
                text = stringResource(CommonString.text_show_my_appointments),
                color = MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18
            )
        }
        CustomSwitch(
            isSwitchOn = true, //TODO
            onSwitch = {
                //TODO
            }
        )
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX32)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        Box(modifier = Modifier.weight(weight = 2.0f)) {
            Text(
                text = stringResource(R.string.text_enable_notifications),
                color = MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18
            )
        }
        CustomSwitch(
            isSwitchOn = true, //TODO
            onSwitch = {
                //TODO
            }
        )
    }
}

@Composable
private fun SocialMediaBlock(
    socialNetwork: SocialNetwork,
    modifier: Modifier = Modifier,
    onValueHabrChange: (String) -> Unit,
    onValueTelegramChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        SpacerHeight(height = MeetTheme.sizes.sizeX40)
        Text(
            text = stringResource(CommonString.text_social_media),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX16)
        InputFieldIcon(
            inputText = socialNetwork.habr.orEmpty(),
            textPlaceholder = stringResource(CommonString.text_habr),
            isEnabled = true,
            leadingIcon = CommonDrawables.ic_leading_habr,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                onValueHabrChange(newValue)
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        InputFieldIcon(
            inputText = socialNetwork.telegram.orEmpty(),
            textPlaceholder = stringResource(CommonString.text_telegram),
            isEnabled = true,
            leadingIcon = CommonDrawables.ic_leading_telegram,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                onValueTelegramChange(newValue)
            }
        )
    }
}

@Composable
private fun InterestsSelectionBlock(
    userInterests: List<Interest>?,
    modifier: Modifier = Modifier,
    onClickUserInterest: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        SpacerHeight(height = MeetTheme.sizes.sizeX40)
        Text(
            text = stringResource(id = CommonString.text_interests),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX10)
        FlexRow(
            horizontalGap = MeetTheme.sizes.sizeX8,
            verticalGap = MeetTheme.sizes.sizeX8,
            alignment = Alignment.Start
        ) {
            userInterests?.let {
                repeat(it.size) { index ->
                    Chip(
                        text = userInterests[index].title,
                        chipSize = ChipSize.MEDIUM,
                        chipColors = ChipSelect.TRUE,
                        chipClick = ChipClick.ON_CLICK,
                        onClick = {
                            onClickUserInterest(userInterests[index].id)
                        }
                    )
                    if (userInterests.size == index + 1) {
                        ChipAddInterests {
                            //TODO
                        }
                    }
                }
            }
            if (userInterests.isNullOrEmpty()) {
                ChipAddInterests {
                    //TODO
                }
            }
        }
    }
}

@Composable
fun ChipAddInterests(
    onAddInterest: () -> Unit
) {
    Chip(
        text = stringResource(CommonString.text_add),
        chipSize = ChipSize.MEDIUM,
        chipColors = ChipSelect.FALSE,
        chipClick = ChipClick.ON_CLICK,
        onClick = {
            onAddInterest()
        }
    )
}

@Composable
private fun BlockInputUserInformation(
    fullName: String,
    phoneNumber: String,
    city: String?,
    bio: String?,
    modifier: Modifier = Modifier,
    onFullNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onCityChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        SpacerHeight(height = MeetTheme.sizes.sizeX40)
        SimpleInputField(
            inputText = fullName,
            textPlaceholder = stringResource(CommonString.text_user_name_surname),
            isEnabled = true,
            state = if (fullName.isEmpty()) InputState.ERROR else InputState.SUCCESS,
            onValueChange = { newValue ->
                onFullNameChange(newValue)
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        SimplePhoneInputFields(
            inputText = phoneNumber,
            textPlaceholder = stringResource(CommonString.text_ph_phone_number_with_code),
            isEnabled = true,
            state = if (phoneNumber.isEmpty()) InputState.ERROR else InputState.SUCCESS,
            onValueChange = { newValue ->
                onPhoneNumberChange(newValue)
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        SimpleInputField(
            inputText = city.orEmpty(),
            textPlaceholder = stringResource(CommonString.text_city),
            isEnabled = true,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                onCityChange(newValue)
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        SimpleInputField(
            inputText = bio.orEmpty(),
            textPlaceholder = stringResource(CommonString.text_tell_us_about_yourself),
            isEnabled = true,
            maxLine = 3,
            singleLine = false,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                onBioChange(newValue)
            }
        )
    }
}

@Composable
private fun AvatarBlock(
    currentPhoto: String?,
    galleryUri: String?,
    editUserViewModel: EditUserViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    Box {
        Box(
            modifier = with(modifier) {
                fillMaxWidth()
            },
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = imageCash(
                    context = LocalContext.current,
                    imageUrl = galleryUri ?: currentPhoto
                ),
                placeholder = painterResource(id = CommonDrawables.ic_avatar_user_profile),
                error = painterResource(id = CommonDrawables.ic_avatar_user_profile),
                contentDescription = stringResource(CommonString.text_avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(height = 375.dp)
            )
            Box(
                modifier = Modifier
                    .padding(bottom = MeetTheme.sizes.sizeX16)
            ) {
                EditChip(
                    text = stringResource(CommonString.text_edit_photo)
                ) {
                    editUserViewModel.updateEditProfileScreenState(state = EditState.EDIT_PICTURE)
                    launcher.launch(editUserViewModel.getChooseImageIntent())
                }
            }
        }
        Row(modifier = Modifier.padding(top = MeetTheme.sizes.sizeX12)) {
            TopAppBar(
                navController = navController,
                containerColor = Color.Transparent
            )
        }
    }
}