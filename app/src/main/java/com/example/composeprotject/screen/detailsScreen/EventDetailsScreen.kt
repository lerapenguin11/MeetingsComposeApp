package com.example.composeprotject.screen.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.avatar.AttendeesRow
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.text.ExpandableText
import com.example.composeprotject.ui.theme.MeetTheme

//TODO: delete text
private const val MAX_LINE_DESC = 8

@Composable
fun EventDetailsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
) {
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
        return;
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
                text = "Lorem ipsum dolor sit amet consectetur. Libero duis cum egestas amet mollis massa. Convallis sit lacus tortor interdum auctor viverra vitae. Egestas aliquam odio aenean eget facilisi ipsum vitae. Risus lectus quam urna condimentum id massa magna id mattis. Sit tempor volutpat ac eget dignissim nibh sagittis vitae duis. Vivamus quis fusce egestas vel sodales arcu praesent non. Ullamcorper elit sit eros egestas euismod amet. Nec molestie a sit sed. At neque diam turpis cursus tincidunt nisi quam sed non. Tempor tortor ultricies ultrices maecenas lectus in nunc sapien dapibus.\n" +
                        "Volutpat placerat et placerat felis tristique quis. Pharetra velit faucibus lobortis vitae dui. Nibh diam velit hendrerit posuere vel ut augue varius velit. Eu eget ipsum vulputate consectetur adipiscing est mollis eleifend quisque. Porttitor senectus nibh molestie faucibus sit mi risus eget. Vivamus dolor ac tortor nibh. Metus amet odio id magna. Augue ac commodo sem varius purus eros eu pharetra nec.\n" +
                        "Bibendum eget donec senectus turpis massa. Magna nunc diam pellentesque egestas sit auctor. Ullamcorper placerat blandit eget scelerisque adipiscing nisi tellus. Aliquam aliquet arcu diam cursus. Egestas duis tellus etiam molestie imperdiet. Tellus turpis purus ligula odio at facilisi. Felis sed in adipiscing ut et amet eros at. Tortor tempor habitasse molestie sed enim condimentum. Purus tellus nec lacus nisl eu sit venenatis elit. Nunc at lacus sit iaculis et volutpat. Elit id vulputate non sed placerat neque parturient egestas. Proin pellentesque bibendum volutpat adipiscing sagittis habitant elit.\n" +
                        "Odio justo dignissim ullamcorper purus ullamcorper sit semper dictum. Tortor est mauris aliquet amet sit ultrices auctor nulla. Faucibus aliquam etiam pharetra pellentesque sagittis odio lacus. Eu morbi senectus in massa fermentum elit in. Tincidunt est blandit malesuada auctor. Orci tellus mus aliquam accumsan ac. Et urna nisl facilisis non volutpat et sodales.\n" +
                        "Malesuada egestas enim purus cras diam eget vel. Massa ante sit scelerisque scelerisque hac. Consequat tempor non pretium convallis. Interdum iaculis sit interdum interdum magna. Gravida urna et cursus donec consectetur nulla. Aliquet egestas nulla arcu aliquam facilisi duis maecenas viverra. Egestas consectetur mauris orci sit. Bibendum orci at viverra pharetra tortor nulla amet erat vehicula. Mauris volutpat amet in sit rhoncus. Imperdiet feugiat id fames gravida."
            )
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX20))
            AttendeesRow(
                avatarList = avatarList()
            )
            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX13))
            FilledButton(
                onClick = {},
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


