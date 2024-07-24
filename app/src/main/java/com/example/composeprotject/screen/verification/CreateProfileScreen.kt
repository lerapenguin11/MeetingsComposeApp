package com.example.composeprotject.screen.verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.avatar.ProfileAvatarContainer
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.input.CustomOutlinedTextField
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.variant.avatar.AvatarState
import com.example.composeprotject.ui.component.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.auth.CreateProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateProfileScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    /* navController: NavHostController,*/
    mainViewModel: MainViewModel = koinViewModel(),
    createProfileViewModel: CreateProfileViewModel = koinViewModel(),
    onGoToGraphEvent: () -> Unit
) {
    mainViewModel.setCurrentScreen(
        screen = NavItem.CreateProfileScreenItem,
        showBottomBar = false,
        showTopBar = true
    )
    var userName by remember { mutableStateOf("") }
    var userSurname by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAvatarContainer(
            variant = ProfileAvatarVariant.MEDIUM,
            colorContainer = MeetTheme.colors.neutralOffWhite,
            avatarUrl = null,
            contentDescription = R.string.text_content_description,
            state = AvatarState.EDITING
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX31))
        CustomOutlinedTextField(
            textPlaceholder = stringResource(id = R.string.text_name),
            isEnabled = true,
            onValueChange = { newValue ->
                userName = newValue
            }
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX12))
        CustomOutlinedTextField(
            textPlaceholder = stringResource(id = R.string.text_surname),
            isEnabled = true,
            onValueChange = { newValue ->
                userSurname = newValue
            }
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX58))
        FilledButton(
            onClick = {
                createProfileViewModel.createProfile(
                    userName = userName,
                    userSurname = userSurname.ifEmpty { null }
                )
                onGoToGraphEvent()
            },
            state = if (userName.isNotEmpty()) ButtonState.INITIAL else ButtonState.DISABLED,
            buttonText = R.string.text_save
        )
    }
}