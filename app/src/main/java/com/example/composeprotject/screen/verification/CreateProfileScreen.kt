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
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.avatar.ProfileAvatarContainer
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.input.CustomOutlinedTextField
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.variant.avatar.AvatarState
import com.example.composeprotject.ui.component.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun CreateProfileScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    navController: NavHostController
) {
    var userName by remember { mutableStateOf("") }

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
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX31))
        CustomOutlinedTextField(
            textPlaceholder = stringResource(id = R.string.text_name),
            isEnabled = true,
            onValueChange = { newValue ->
                userName = newValue
            }
        )
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX12))
        CustomOutlinedTextField(
            textPlaceholder = stringResource(id = R.string.text_surname),
            isEnabled = true,
            onValueChange = {/*TODO*/}
        )
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX68))
        FilledButton(
            onClick = {
                navController.navigate(route = NavItem.EventItem.route) /*TODO: докинуть логики*/
            },
            state = if (userName.isNotEmpty()) ButtonState.INITIAL else ButtonState.DISABLED,
            buttonText = R.string.text_save
        )
    }
}