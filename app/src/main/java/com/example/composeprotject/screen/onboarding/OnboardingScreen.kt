package com.example.composeprotject.screen.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.model.interest.Interest
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
        Text(
            text = stringResource(R.string.text_interests),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold49
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX12))
        Text(
            text = stringResource(R.string.text_interests_desc),
            color = Color.Black,
            style = MeetTheme.typography.interRegular19
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX24))
        FlexRow(
            horizontalGap = MeetTheme.sizes.sizeX8,
            verticalGap = MeetTheme.sizes.sizeX8,
            alignment = Alignment.Start
        ) {
            repeat(interests().size) { index ->
                Chip(
                    text = interests()[index].title,
                    chipSize = ChipSize.BIG,
                    chipColors = ChipClick.FALSE
                ) {
                    //TODO
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
                state = FilledButtonState.DISABLED,
                buttonText = stringResource(id = R.string.text_save)
            ) {
                /*TODO*/
            }
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
            Text(
                modifier = Modifier.clickable { /*TODO*/ },
                text = stringResource(R.string.text_tell_later),
                color = MeetTheme.colors.darkGray,
                style = MeetTheme.typography.interMedium18
            )
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

private fun interests(): List<Interest> {
    val interestList = listOf(
        Interest(
            id = 0, title = "Дизайн"
        ),
        Interest(
            id = 1, title = "Разработка"
        ),
        Interest(
            id = 2, title = "Продакт менеджмент"
        ),
        Interest(
            id = 3, title = "Проджект менеджмент"
        ),
        Interest(
            id = 4, title = "Backend"
        ),
        Interest(
            id = 5, title = "Frontend"
        ),
        Interest(
            id = 6, title = "Mobile"
        ),
        Interest(
            id = 7, title = "Web"
        ),
        Interest(
            id = 8, title = "Тестирование"
        ),
        Interest(
            id = 9, title = "Продажи"
        ),
        Interest(
            id = 10, title = "Бизнес"
        ),
        Interest(
            id = 11, title = "Маркетинг"
        ),
        Interest(
            id = 12, title = "Безопасность"
        ),
        Interest(
            id = 13, title = "Девопс"
        ),
        Interest(
            id = 14, title = "Аналитика"
        )
    )
    return interestList
}