package com.example.composeprotject.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.EditChip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.input.InputFieldIcon
import com.example.composeprotject.ui.component.input.SimpleInputField
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.switcher.CustomSwitch
import com.example.composeprotject.ui.component.topBar.standard.TopAppBar
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.domain.model.interest.Interest

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        navController = rememberNavController(),
        onGoProfileAfterSaving = {},
        isSaveData = true
    )
}

@Composable
fun EditProfileScreen(
    navController: NavController,
    isSaveData: Boolean,
    modifier: Modifier = Modifier,
    onGoProfileAfterSaving: (Boolean) -> Unit
) {
    LaunchedEffect(isSaveData) {
        if (isSaveData) {
            println("SAVE")
            kotlinx.coroutines.delay(5000)
            onGoProfileAfterSaving(true)
        }
    }
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            AvatarBlock(
                avatarUrl = null,
                navController = navController
            )
        }
        item {
            BlockInputUserInformation()
        }
        item {
            InterestsSelectionBlock(
                userInterests = listOf(
                    Interest(id = 0, "Разработка"),
                    Interest(id = 0, "Дизайн"),
                    Interest(id = 0, "Frontend")
                )
            )
        }
        item {
            SocialMediaBlock()
        }
        item {
            NotificationBlock()
        }
        item {
            DeleteProfileBlock()
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
    modifier: Modifier = Modifier
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
            textPlaceholder = stringResource(CommonString.text_habr),
            isEnabled = true,
            leadingIcon = CommonDrawables.ic_leading_habr,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                //TODO
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        InputFieldIcon(
            textPlaceholder = stringResource(CommonString.text_telegram),
            isEnabled = true,
            leadingIcon = CommonDrawables.ic_leading_telegram,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                //TODO
            }
        )
    }
}

@Composable
private fun InterestsSelectionBlock(
    userInterests: List<Interest>,
    modifier: Modifier = Modifier
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
            repeat(userInterests.size) { index ->
                Chip(
                    text = userInterests[index].title,
                    chipSize = ChipSize.MEDIUM,
                    chipColors = ChipSelect.TRUE,
                    chipClick = ChipClick.ON_CLICK
                ) {
                    //TODO
                }
                if (userInterests.size == index + 1 || userInterests.isEmpty()) {
                    Chip(
                        text = stringResource(CommonString.text_add),
                        chipSize = ChipSize.MEDIUM,
                        chipColors = ChipSelect.FALSE,
                        chipClick = ChipClick.ON_CLICK
                    ) {
                        //TODO
                    }
                }
            }
        }
    }
}

@Composable
private fun BlockInputUserInformation(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        SpacerHeight(height = MeetTheme.sizes.sizeX40)
        SimpleInputField(
            textPlaceholder = stringResource(CommonString.text_user_name_surname),
            isEnabled = true,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                //TODO
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        SimpleInputField(
            textPlaceholder = stringResource(CommonString.text_ph_phone_number_with_code),
            isEnabled = true,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                //TODO
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        SimpleInputField(
            textPlaceholder = stringResource(CommonString.text_city),
            isEnabled = true,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                //TODO
            }
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        SimpleInputField(
            textPlaceholder = stringResource(CommonString.text_tell_us_about_yourself),
            isEnabled = true,
            maxLine = 3,
            singleLine = false,
            state = InputState.SUCCESS,
            onValueChange = { newValue ->
                //TODO
            }
        )
    }
}

@Composable
private fun AvatarBlock(
    avatarUrl: String?,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box {
        Box(
            modifier = with(modifier) {
                fillMaxWidth()
            },
            contentAlignment = Alignment.BottomCenter
        ) {
            //TODO add top app bar
            AsyncImage(
                model = imageCash(
                    context = LocalContext.current,
                    imageUrl = avatarUrl
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
                    //TODO
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