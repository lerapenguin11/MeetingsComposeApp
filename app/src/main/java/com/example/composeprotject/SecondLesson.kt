package com.example.composeprotject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.avatar.AttendeesRow
import com.example.composeprotject.ui.component.avatar.ProfileAvatarContainer
import com.example.composeprotject.ui.component.card.CommunitiesCard
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.component.variant.avatar.AvatarState
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun SecondLesson(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ){
        UIKitEventCards()
        UIKitCommunitiesCards()
        UIKitProfileAvatarRow()
        UIKitProfileAvatars()
    }
}

@Composable
fun UIKitProfileAvatars() {
    Row(Modifier.padding(end = 10.dp)) {
        ProfileAvatarContainer(
            variant = ProfileAvatarVariant.MEDIUM,
            colorContainer = MeetTheme.colors.neutralOffWhite,
            avatarUrl = null,
            contentDescription = R.string.text_content_description,
            state = AvatarState.EDITING)

        ProfileAvatarContainer(
            variant = ProfileAvatarVariant.MEDIUM,
            colorContainer = MeetTheme.colors.neutralOffWhite,
            avatarUrl = null,
            contentDescription = R.string.text_content_description,
            state = AvatarState.DISPLAY)
    }

}

@Composable
fun UIKitProfileAvatarRow() {
    Column(Modifier.padding(top = 10.dp)) {
        val avatarList = listOf(
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
            "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg"
        )
        AttendeesRow(avatarList = avatarList)
    }
}

@Composable
fun UIKitCommunitiesCards() {
    Column {
        CommunitiesCard(
            placeholderImage = R.drawable.ic_avatar_meetings,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 1000000
        )
        CommunitiesCard(
            placeholderImage = R.drawable.ic_avatar_meetings,
            avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
            nameGroup = "Designa",
            numberPeople = 100000
        )
    }
}

@Composable
fun UIKitEventCards() {
    val textChipList = listOf(
        "Python",
        "Junior",
        "Moscow"
    )
    Column(modifier = Modifier.fillMaxWidth()) {
            textChipList.forEach {
                EventCard(
                    placeholderImage = R.drawable.ic_avatar_meetings,
                    tags = textChipList,
                    dateLocation ="13.09.2024 — Москва" ,
                    meetingName = "Developer meeting",
                    avatarUrl = "https://fikiwiki.com/uploads/posts/2022-02/1644881323_42-fikiwiki-com-p-krasivie-kartinki-pingvinov-49.jpg")
            }
    }
}