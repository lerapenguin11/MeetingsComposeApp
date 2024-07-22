package com.example.composeprotject.screen.detailsScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.text.ExpandableText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.details.CommunityDetailsViewModel
import org.koin.androidx.compose.koinViewModel

private const val MAX_LINE_DESC = 13

@Composable
fun CommunityDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    communityId: Int?,
    contentPadding: PaddingValues,
    communityName: String?,
    viewModel: MainViewModel,
    communityDetailsViewModel: CommunityDetailsViewModel = koinViewModel()
) {
    viewModel.setCurrentScreen(
        screen = NavItem.CommunityDetailsItem,
        showTopBar = true,
        showBottomBar = true
    )
    viewModel.setTitleDetailedScreen(
        communityName ?: stringResource(id = R.string.text_community_details)
    )
    val community by communityDetailsViewModel.getCommunityDetailsFlow().collectAsState()
    communityId?.let {
        communityDetailsViewModel.getCommunityById(communityId = it)
    }
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        community?.let {
            item {
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
                ExpandableText(
                    maxLines = MAX_LINE_DESC,
                    text = it.communityDescription
                )
                Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX30))
                BaseText(
                    text = stringResource(id = R.string.text_event_community),
                    textStyle = MeetTheme.typography.bodyText1,
                    textColor = MeetTheme.colors.neutralWeak
                )
            }
            items(it.communityMeetings.filter { communityId == it.communityId }) { event ->
                EventCard(
                    meetingName = event.meetingName,
                    dateLocation = event.dateLocation,
                    tags = event.tags,
                    avatarUrl = event.avatarUrl,
                    placeholderImage = R.drawable.ic_avatar_meetings,
                    isActiveMeet = event.activeEvent,
                    onClick = {/*TODO*/ }
                )
            }
        }
    }
}