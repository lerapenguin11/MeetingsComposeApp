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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeprotject.screen.state.SubscriptionCapabilityStatus
import com.example.composeprotject.ui.component.card.CommunityCard
import com.example.composeprotject.ui.component.card.CommunityViewAllCard
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventViewAllCard
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
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import com.example.domain.model.interest.Category
import com.example.domain.model.interest.Interest

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview() {
    ProfileScreen()
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
        /*     .windowInsetsTopHeight(insets = WindowInsets.statusBars)*/
    ) {
        item {
            AvatarBlock(
                avatarUrl = null
            )
        }
        item {
            BlockUserInformation(
                name = "Сергей",
                city = "Москва",
                bio = "Занимаюсь разрабокой интерфейсов в eCom. Учу HTML, CSS и JavaScript",
                userInterest = listOf(
                    Interest(id = 0, "Разработка"),
                    Interest(id = 0, "Дизайн"),
                    Interest(id = 0, "Illustrator"),
                    Interest(id = 0, "Backend"),
                    Interest(id = 0, "Продакт менеджмент")
                )
            )
        }
        //TODO: мои встречи
        item {
            MyMeetingsBlock(
                myMeeting = listOf(
                    Meeting(
                        id = 1,
                        title = "Python days",
                        categories = listOf(Category(id = 0, "Разработка")),
                        avatarUrl = null,
                        startDate = 9,
                        shortAddress = "Большая Конюшенная, 10"
                    )
                )
            ) {
                //TODO
            }
        }
        //TODO: мои встречи
        //TODO: мои сообщества
        item {
            MyCommunityBlock(
                communities = listOf(
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                    Community(
                        id = 0,
                        title = "WBTECH",
                        avatarUrl = null
                    ),
                )
            ) {
                //TODO
            }
        }
        //TODO: мои сообщества
        item { LogOutOfProfileBlock() }
    }
}

@Composable
private fun LogOutOfProfileBlock(
    modifier: Modifier = Modifier
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
                        //TODO
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
    communities: List<Community>,
    modifier: Modifier = Modifier,
    onClickCommunity: (Community) -> Unit
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
            CommunityCard(
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
    myMeeting: List<Meeting>,
    modifier: Modifier = Modifier,
    onClickEvent: (Meeting) -> Unit
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
            EventCard(
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
    city: String,
    bio: String,
    userInterest: List<Interest>,
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
        SpacerHeight(height = MeetTheme.sizes.sizeX4)
        Text(
            text = city,
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold14
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX2)
        Text(
            text = bio,
            color = Color.Black,
            style = MeetTheme.typography.interMedium14
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX16)
        FlexRow(
            horizontalGap = MeetTheme.sizes.sizeX6,
            verticalGap = MeetTheme.sizes.sizeX6,
            alignment = Alignment.Start
        ) {
            repeat(userInterest.size) { index ->
                Chip(
                    text = userInterest[index].title,
                    chipSize = ChipSize.SMALL,
                    chipColors = ChipSelect.FALSE,
                    chipClick = ChipClick.NOT_ON_CLICK
                ) {}
            }
        }
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

@Composable
private fun AvatarBlock(
    avatarUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = with(modifier) {
            fillMaxWidth()
        }
    ) {
        //TODO add top app bar
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
    }
}

private const val MAX_ELEMENT = 6
