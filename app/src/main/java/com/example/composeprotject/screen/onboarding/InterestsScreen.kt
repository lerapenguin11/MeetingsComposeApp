package com.example.composeprotject.screen.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.InterestsViewModel
import com.example.domain.model.interest.Interest
import org.koin.androidx.compose.koinViewModel

@Composable
fun InterestsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    interestsViewModel: InterestsViewModel = koinViewModel(),
    onClickTellLater: () -> Unit
) {
    val interests by interestsViewModel.getInterestsFlow().collectAsStateWithLifecycle()
    val uiState by interestsViewModel.getUIStateFlow().collectAsStateWithLifecycle()
    val userInterests by interestsViewModel.getUserInterests().collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
        Text(
            text = stringResource(CommonString.text_interests),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold49
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX12))
        Text(
            text = stringResource(CommonString.text_interests_desc),
            color = Color.Black,
            style = MeetTheme.typography.interRegular19
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX24))
        FlexRow(
            horizontalGap = MeetTheme.sizes.sizeX8,
            verticalGap = MeetTheme.sizes.sizeX8,
            alignment = Alignment.Start
        ) {
            repeat(interests.size) { index ->
                Chip(
                    text = interests[index].title,
                    chipSize = ChipSize.BIG,
                    chipColors = if (checkingUserNoSuchInterest(
                            userInterests,
                            interests[index].id
                        )
                    ) ChipSelect.FALSE else ChipSelect.TRUE,
                    chipClick = ChipClick.ON_CLICK
                ) {
                    interestsViewModel.toggleUserInterest(interests[index])
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledButton(
                state = if (userInterests.isNotEmpty()) FilledButtonState.ACTIVE_PRIMARY else FilledButtonState.DISABLED,
                buttonText = stringResource(id = CommonString.text_save)
            ) {
                /*TODO*/
            }
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                Text(
                    modifier = Modifier.clickable {
                        onClickTellLater()
                    },
                    text = stringResource(CommonString.text_tell_later),
                    color = MeetTheme.colors.darkGray,
                    style = MeetTheme.typography.interMedium18
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

private fun checkingUserNoSuchInterest(userInterests: List<Interest>, id: Int): Boolean {
    return userInterests.none { it.id == id }
}