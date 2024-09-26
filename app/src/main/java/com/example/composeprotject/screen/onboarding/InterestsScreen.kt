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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.screen.state.InterestState
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
import com.example.composeprotject.utils.checkingUserNoSuchInterest
import com.example.composeprotject.viewModel.InterestsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InterestsScreen(
    contentPadding: PaddingValues,
    screenState: InterestState,
    modifier: Modifier = Modifier,
    interestsViewModel: InterestsViewModel = koinViewModel(),
    onClickSkip: () -> Unit,
    onClockGoMainGraph: () -> Unit
) {
    val uiState by interestsViewModel.getUIStateFlow().collectAsStateWithLifecycle()
    val combinedInterests by interestsViewModel.getCombinedInterests().collectAsStateWithLifecycle()
    val buttonState by interestsViewModel.getButtonState().collectAsStateWithLifecycle()

    var test by remember { mutableStateOf(FilledButtonState.ACTIVE_PRIMARY) }
    test = buttonState

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
            repeat(combinedInterests.first.size) { index ->
                Chip(
                    text = combinedInterests.first[index].title,
                    chipSize = ChipSize.BIG,
                    chipColors = if (checkingUserNoSuchInterest(
                            combinedInterests.second,
                            combinedInterests.first[index].id
                        )
                    ) ChipSelect.FALSE else ChipSelect.TRUE,
                    chipClick = ChipClick.ON_CLICK,
                    onClick = { interestsViewModel.toggleUserInterest(combinedInterests.first[index]) }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledButton(
                state = if (combinedInterests.second.isNotEmpty()) buttonState else FilledButtonState.DISABLED,
                buttonText = stringResource(id = CommonString.text_save)
            ) {
                interestsViewModel.saveOnBoardingInterestState() //TODO зависит от стейта экрана
                interestsViewModel.addUserInterests(
                    userInterests = combinedInterests.second,
                    stateScreen = screenState
                )
            }
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                Text(
                    modifier = Modifier.clickable {
                        onClickSkip()
                    },
                    text = stringResource(CommonString.text_tell_later),
                    color = MeetTheme.colors.darkGray,
                    style = MeetTheme.typography.interMedium18
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
    }

    LaunchedEffect(buttonState) {
        if (buttonState == FilledButtonState.ACTIVE_SECONDARY) {
            onClockGoMainGraph()
        }
    }
}