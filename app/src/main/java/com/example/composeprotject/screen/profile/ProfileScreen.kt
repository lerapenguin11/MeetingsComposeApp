package com.example.composeprotject.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeprotject.screen.state.SubscriptionCapabilityStatus
import com.example.composeprotject.ui.component.card.CommunityViewAllCard
import com.example.composeprotject.ui.component.card.EventViewAllCard
import com.example.composeprotject.ui.component.card.UserCommunityCard
import com.example.composeprotject.ui.component.card.UserEventCard
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.network.SocialNetwork
import com.example.composeprotject.ui.component.network.variant.SocialNetworkVariant
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.spacer.SpacerWidth
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.topBar.standard.TopAppBar
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.ProfileViewModel
import com.example.domain.model.interest.Interest
import com.example.domain.model.userLists.UserCommunities
import com.example.domain.model.userLists.UserEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = koinViewModel(),
    logOutOfProfile: () -> Unit
) {
    val isShowSettings by profileViewModel.getIsShowSettingsMyListsFlow()
        .collectAsStateWithLifecycle()
    val fullUserInfoFlow by profileViewModel.getFullUserInfoFlow().collectAsStateWithLifecycle()

    LaunchedEffect(isShowSettings) {
        isShowSettings.authToken?.let {
            profileViewModel.loadUserInfo(
                isShowMyEvents = isShowSettings.isShowMyEvents,
                isShowMyCommunities = isShowSettings.isShowMyCommunities,
                authToken = it
            )
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            AvatarBlock(
                navController = navController,
                avatarUrl = fullUserInfoFlow.userInfo?.avatarUrl
            )
        }
        item {
            fullUserInfoFlow.userInfo?.let {
                BlockUserInformation(
                    name = it.fullName,
                    city = it.city,
                    bio = it.bio,
                    userInterest = it.interests,
                    habr = it.socialNetwork.habr,
                    telegram = it.socialNetwork.telegram
                )
            }
        }
        item {
            fullUserInfoFlow.userEvents?.let {
                MyMeetingsBlock(
                    myMeeting = it
                ) {
                    //TODO
                }
            }
        }
        item {
            fullUserInfoFlow.userCommunities?.let {
                MyCommunityBlock(
                    communities = it
                ) {
                    //TODO
                }
            }
        }
        item {
            LogOutOfProfileBlock(
                logOutOfProfile = {
                    profileViewModel.deleteAuthToken()
                    logOutOfProfile()
                }
            )
        }
    }
}

@Composable
private fun LogOutOfProfileBlock(
    modifier: Modifier = Modifier,
    logOutOfProfile: () -> Unit
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
                        logOutOfProfile()
                    },
                text = stringResource(CommonString.text_exit),
                color = MeetTheme.colors.darkGray,
                style = MeetTheme.typography.interMedium18
            )
        }
    }
}

@Composable
private fun MyCommunityBlock(
    communities: List<UserCommunities>,
    modifier: Modifier = Modifier,
    onClickCommunity: (UserCommunities) -> Unit
) {
    SpacerHeight(height = MeetTheme.sizes.sizeX40)
    Text(
        modifier = modifier
            .padding(start = MeetTheme.sizes.sizeX16),
        text = stringResource(CommonString.text_my_community),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX10)
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        itemsIndexed(communities) { _, community ->
            UserCommunityCard(
                state = SubscriptionCapabilityStatus.WITHOUT_SUBSCRIPTION,
                community = community,
                buttonState = SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY
            ) {
                onClickCommunity(community)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            if (communities.size > MAX_ELEMENT) {
                CommunityViewAllCard {/*TODO*/ }
                SpacerWidth(width = MeetTheme.sizes.sizeX16)
            }
        }
        item {
            if (communities.size <= MAX_ELEMENT) {
                SpacerWidth(width = MeetTheme.sizes.sizeX6)
            }
        }
    }
}

@Composable
private fun MyMeetingsBlock(
    myMeeting: List<UserEvents>,
    modifier: Modifier = Modifier,
    onClickEvent: (UserEvents) -> Unit
) {
    SpacerHeight(height = MeetTheme.sizes.sizeX40)
    Text(
        modifier = modifier
            .padding(start = MeetTheme.sizes.sizeX16),
        text = stringResource(id = CommonString.text_my_event),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX10)
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        itemsIndexed(myMeeting) { index, meeting ->
            UserEventCard(
                meeting = meeting,
                variant = EventCardVariant.MEDIUM
            ) {
                onClickEvent(meeting)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            if (myMeeting.size > MAX_ELEMENT) {
                EventViewAllCard(
                    variant = EventCardVariant.SMALL
                ) {/*TODO*/ }
                SpacerWidth(width = MeetTheme.sizes.sizeX16)
            }
        }
        item {
            if (myMeeting.size <= MAX_ELEMENT) {
                SpacerWidth(width = MeetTheme.sizes.sizeX6)
            }
        }
    }
}

@Composable
private fun BlockUserInformation(
    name: String,
    city: String?,
    bio: String?,
    habr: String?,
    telegram: String?,
    userInterest: List<Interest>?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        SpacerHeight(height = MeetTheme.sizes.sizeX20)
        Text(
            text = name,
            style = MeetTheme.typography.interSemiBold49,
            color = MeetTheme.colors.neutralActive
        )
        city?.let {
            SpacerHeight(height = MeetTheme.sizes.sizeX4)
            Text(
                text = it,
                color = Color.Black,
                style = MeetTheme.typography.interSemiBold14
            )
        }
        bio?.let {
            SpacerHeight(height = MeetTheme.sizes.sizeX2)
            Text(
                text = it,
                color = Color.Black,
                style = MeetTheme.typography.interMedium14
            )
        }
        userInterest?.let {
            SpacerHeight(height = MeetTheme.sizes.sizeX16)
            FlexRow(
                horizontalGap = MeetTheme.sizes.sizeX6,
                verticalGap = MeetTheme.sizes.sizeX6,
                alignment = Alignment.Start
            ) {
                repeat(it.size) { index ->
                    Chip(
                        text = userInterest[index].title,
                        chipSize = ChipSize.SMALL,
                        chipColors = ChipSelect.FALSE,
                        chipClick = ChipClick.NOT_ON_CLICK
                    ) {}
                }
            }
        }
        if (habr != null || telegram != null) {
            SpacerHeight(height = MeetTheme.sizes.sizeX16)
            Row {
                SocialNetwork(variant = SocialNetworkVariant.HABR) {
                    //TODO
                }
                SpacerWidth(width = MeetTheme.sizes.sizeX8)
                SocialNetwork(variant = SocialNetworkVariant.TELEGRAM) {
                    //TODO
                }
            }
        }
    }
}

@Composable
private fun AvatarBlock(
    navController: NavController,
    modifier: Modifier = Modifier,
    avatarUrl: String? = null
) {
    Box(
        modifier = with(modifier) {
            fillMaxWidth()
        }
    ) {
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
        Row(modifier = Modifier.padding(top = MeetTheme.sizes.sizeX12)) {
            TopAppBar(
                navController = navController,
                containerColor = Color.Transparent
            )
        }
    }
}

private const val MAX_ELEMENT = 6
