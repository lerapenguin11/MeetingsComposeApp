package com.example.composeprotject.screen_old

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component_old.avatar.ProfileAvatarContainer
import com.example.composeprotject.ui.component_old.button.ImageOutlinedButton
import com.example.composeprotject.ui.component_old.variant.avatar.AvatarState
import com.example.composeprotject.ui.component_old.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.getUserFullName
import com.example.composeprotject.viewModel_old.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    profileViewModel: ProfileViewModel = koinViewModel()
) {
    val userInfo by profileViewModel.getInfoUserProfileFlow().collectAsState()

    Column(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        userInfo?.let { user ->
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX46))
            ProfileAvatarContainer(
                variant = ProfileAvatarVariant.LARGE,
                colorContainer = MeetTheme.colors.neutralOffWhite,
                avatarUrl = null,
                contentDescription = R.string.text_content_description,
                state = AvatarState.DISPLAY
            )
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
            BaseText(
                text = getUserFullName(user.userName, user.userSurname),
                textColor = MeetTheme.colors.neutralActive,
                textStyle = MeetTheme.typography.sfProDisplaySemibold24
            )
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
            BaseText(
                text = user.phoneNumber,
                textColor = MeetTheme.colors.neutralDisabled,
                textStyle = MeetTheme.typography.sfProDisplayRegular16
            )
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX40))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ImageOutlinedButton(
                    icon = R.drawable.ic_twitter,
                    contentDescription = R.string.text_content_desc_twitter,
                    onClick = {/*TODO*/ })
                ImageOutlinedButton(
                    icon = R.drawable.ic_instagram,
                    contentDescription = R.string.text_content_desc_instagram,
                    onClick = {/*TODO*/ })
                ImageOutlinedButton(
                    icon = R.drawable.ic_linkedin,
                    contentDescription = R.string.text_content_desc_linkedIn,
                    onClick = {/*TODO*/ })
                ImageOutlinedButton(
                    icon = R.drawable.ic_facebook,
                    contentDescription = R.string.text_content_desc_facebook,
                    onClick = {/*TODO*/ })
            }
        }
    }
}