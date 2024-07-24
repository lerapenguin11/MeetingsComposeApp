package com.example.composeprotject.screen.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.avatar.AttendeesRow
import com.example.composeprotject.ui.component.button.ToggleMeetingButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.text.ExpandableText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.details.EventDetailsViewModel
import org.koin.androidx.compose.koinViewModel

private const val MAX_LINE_DESC = 8

@Composable
fun EventDetailsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    eventId: Int?,
    eventDetailsViewModel: EventDetailsViewModel = koinViewModel()
) {
    eventId?.let {
        eventDetailsViewModel.getEventDetails(eventId = it)
    }

    val event by eventDetailsViewModel.getEventDetailsFlow().collectAsState()
    var isMapExpanded by remember { mutableStateOf(false) }
    var showMeeting by remember { mutableStateOf(true) }
    eventDetailsViewModel.setActionEventDetails(isAction = showMeeting)

    if (isMapExpanded) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { isMapExpanded = false },
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_map),
            contentDescription = stringResource(R.string.text_map)
        )
        return
    }

    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        event?.let {
            item {
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
                BaseText(
                    text = "${it.dateLocation}, ${it.address}", //TODO
                    textStyle = MeetTheme.typography.bodyText1,
                    textColor = MeetTheme.colors.neutralWeak
                )
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX2))
                Row(horizontalArrangement = Arrangement.spacedBy(MeetTheme.sizes.sizeX4)) {
                    it.tags.forEach { textChip ->
                        Chip(text = textChip)
                    }
                }
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX12))
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(height = 175.dp)
                        .clip(RoundedCornerShape(MeetTheme.sizes.sizeX24))
                        .clickable { isMapExpanded = true },
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = stringResource(id = R.string.text_map)
                )
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
                ExpandableText(
                    maxLines = MAX_LINE_DESC,
                    text = it.eventDescription
                )
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
                AttendeesRow(
                    avatarList = it.avatarList
                )
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX13))
                ToggleMeetingButton(
                    onClick = {
                        showMeeting = !showMeeting
                    },
                    showMeeting = showMeeting,
                    textOutlineButton = R.string.text_go_to_another_time,
                    textFieldButton = R.string.text_go_to_meeting
                )
            }
        }

    }
}


