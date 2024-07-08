package com.example.composeprotject.screen.detailsScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.model.CommunityMeetings
import com.example.composeprotject.navigation.BottomNavItem
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun CommunityDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    communityId: Int?,
    contentPadding: PaddingValues,
    communityName: String?,
    viewModel: MainViewModel,
) {
    viewModel.setCurrentScreen(BottomNavItem.CommunityDetailsItem)
    viewModel.setTitleDetailedScreen(communityName ?: "Community name")
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        item {
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
            ExpandableText(
                text = "Lorem ipsum dolor sit amet consectetur. Libero duis cum egestas amet mollis massa. Convallis sit lacus tortor interdum auctor viverra vitae. Egestas aliquam odio aenean eget facilisi ipsum vitae. Risus lectus quam urna condimentum id massa magna id mattis. Sit tempor volutpat ac eget dignissim nibh sagittis vitae duis. Vivamus quis fusce egestas vel sodales arcu praesent non. Ullamcorper elit sit eros egestas euismod amet. Nec molestie a sit sed. At neque diam turpis cursus tincidunt nisi quam sed non. Tempor tortor ultricies ultrices maecenas lectus in nunc sapien dapibus.\n" +
                        "Volutpat placerat et placerat felis tristique quis. Pharetra velit faucibus lobortis vitae dui. Nibh diam velit hendrerit posuere vel ut augue varius velit. Eu eget ipsum vulputate consectetur adipiscing est mollis eleifend quisque. Porttitor senectus nibh molestie faucibus sit mi risus eget. Vivamus dolor ac tortor nibh. Metus amet odio id magna. Augue ac commodo sem varius purus eros eu pharetra nec.\n" +
                        "Bibendum eget donec senectus turpis massa. Magna nunc diam pellentesque egestas sit auctor. Ullamcorper placerat blandit eget scelerisque adipiscing nisi tellus. Aliquam aliquet arcu diam cursus. Egestas duis tellus etiam molestie imperdiet. Tellus turpis purus ligula odio at facilisi. Felis sed in adipiscing ut et amet eros at. Tortor tempor habitasse molestie sed enim condimentum. Purus tellus nec lacus nisl eu sit venenatis elit. Nunc at lacus sit iaculis et volutpat. Elit id vulputate non sed placerat neque parturient egestas. Proin pellentesque bibendum volutpat adipiscing sagittis habitant elit.\n" +
                        "Odio justo dignissim ullamcorper purus ullamcorper sit semper dictum. Tortor est mauris aliquet amet sit ultrices auctor nulla. Faucibus aliquam etiam pharetra pellentesque sagittis odio lacus. Eu morbi senectus in massa fermentum elit in. Tincidunt est blandit malesuada auctor. Orci tellus mus aliquam accumsan ac. Et urna nisl facilisis non volutpat et sodales.\n" +
                        "Malesuada egestas enim purus cras diam eget vel. Massa ante sit scelerisque scelerisque hac. Consequat tempor non pretium convallis. Interdum iaculis sit interdum interdum magna. Gravida urna et cursus donec consectetur nulla. Aliquet egestas nulla arcu aliquam facilisi duis maecenas viverra. Egestas consectetur mauris orci sit. Bibendum orci at viverra pharetra tortor nulla amet erat vehicula. Mauris volutpat amet in sit rhoncus. Imperdiet feugiat id fames gravida.")
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX30))
            BaseText(
                text = stringResource(id = R.string.text_event_community),
                textStyle = MeetTheme.typography.bodyText1,
                textColor = MeetTheme.colors.neutralWeak)
        }
        items(communityEventList().filter { communityId == it.communityId }){ event ->
            EventCard(
                meetingName = event.meetingName,
                dateLocation = event.dateLocation,
                tags = event.tags,
                avatarUrl = event.avatarUrl,
                placeholderImage = R.drawable.ic_avatar_meetings,
                isActiveMeet = event.activeEvent,
                onClick = {/*TODO*/}
            )
        }
    }
}

private fun communityEventList() = listOf<CommunityMeetings>(
    CommunityMeetings(
        communityId = 1,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        activeEvent = true
    ),
    CommunityMeetings(
        communityId = 1,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        activeEvent = false
    ),
    CommunityMeetings(
        communityId = 2,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        activeEvent = true
    ),
    CommunityMeetings(
        communityId = 3,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        activeEvent = false
    ),
    CommunityMeetings(
        communityId = 3,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        activeEvent = true
    )
)

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int = 13
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .animateContentSize(animationSpec = tween(100))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { isExpanded = !isExpanded }) {
        if (isExpanded) {
            BaseText(
                text = text,
                textStyle = MeetTheme.typography.metadata1,
                textColor = MeetTheme.colors.neutralWeak)
        } else {
            Text(
                text = text,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                color = MeetTheme.colors.neutralWeak,
                style = MeetTheme.typography.metadata1)
        }
    }
}
