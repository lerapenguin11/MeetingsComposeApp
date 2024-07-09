package com.example.composeprotject.screen.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.avatar.AttendeesRow
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.text.ExpandableText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

//TODO: delete text
private const val MAX_LINE_DESC = 8

@Composable
fun EventDetailsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    eventId: Int?,
    eventName: String?,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(NavItem.EventDetailsItem)
    viewModel.setTitleDetailedScreen(
        eventName ?: stringResource(id = R.string.text_event_details)
    )
    var isMapExpanded by remember { mutableStateOf(false) }

    if (isMapExpanded) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { isMapExpanded = false },
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_map),
            contentDescription = "Map"
        )
        return
    }

    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        item {
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
            BaseText(
                text = "13.09.2024 — Москва, ул. Громова, 4",
                textStyle = MeetTheme.typography.bodyText1,
                textColor = MeetTheme.colors.neutralWeak
            )
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX2))
            Row(horizontalArrangement = Arrangement.spacedBy(MeetTheme.sizes.sizeX4)) {
                val chips = listOf("Python", "Junior", "Moscow")
                chips.forEach { textChip ->
                    Chip(text = textChip)
                }
            }
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX12))
            Image(
                modifier = modifier
                    .fillMaxSize()
                    .height(height = 175.dp)
                    .clip(RoundedCornerShape(MeetTheme.sizes.sizeX24))
                    .clickable { isMapExpanded = true },
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ic_map),
                contentDescription = "Map"
            )
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX20))
            ExpandableText(
                maxLines = MAX_LINE_DESC,
                text = LoremIpsum(300).values.first()
            )
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX20))
            AttendeesRow(
                avatarList = avatarList()
            )
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX13))
            FilledButton(
                onClick = { /*TODO*/ },
                buttonText = R.string.text_go_to_meeting
            )
        }
    }
}

fun avatarList() = listOf(
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
    "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg"
)


