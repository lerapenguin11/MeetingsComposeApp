package com.example.composeprotject.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.screen.navScreen.Community
import com.example.composeprotject.ui.component.avatar.RoundedAvatarMeetings
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.divider.StandardDivider
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

//TODO: вынести в модель

@Composable
fun EventCard(
    meetingName : String,
    dateLocation : String,
    tags : List<String>,
    placeholderImage : Int,
    avatarUrl : String,
    modifier: Modifier = Modifier,
    isActiveMeet : Boolean = true){
    Column(modifier = modifier.background(color = MeetTheme.colors.neutralWhite)) {
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            Row(modifier = modifier, verticalAlignment = Alignment.Top) {
                RoundedAvatarMeetings(placeholderImage = placeholderImage, avatarUrl = avatarUrl)
                Spacer(modifier = modifier.width(MeetTheme.sizes.sizeX12))
                Column {
                    BaseText(text = meetingName,
                        textColor = MeetTheme.colors.neutralActive,
                        textStyle = MeetTheme.typography.bodyText1)
                    Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX2))
                    BaseText(text = dateLocation, modifier = modifier,
                        textColor = MeetTheme.colors.neutralWeak,
                        textStyle = MeetTheme.typography.metadata1)
                    Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX2))
                    Row(horizontalArrangement = Arrangement.spacedBy(MeetTheme.sizes.sizeX4)) {
                        tags.forEach { tag ->
                            Chip(text = tag)
                        }
                    }
                }
                if (!isActiveMeet){
                    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End) {
                        Text(
                            text = stringResource(id = R.string.text_inactive_event),
                            color = MeetTheme.colors.neutralWeak,
                            style = MeetTheme.typography.metadata2)
                    }
                }
            }
        }
    }
}

@Composable
fun CommunitiesCard(
    placeholderImage : Int,
    modifier: Modifier = Modifier,
    community: Community
){
    val textNumberPeople = "${formatNumberWithSpaces(community.numberPeople)} ${stringResource(id = R.string.text_people)}"
    Column(modifier = modifier.background(color = MeetTheme.colors.neutralWhite)) {
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            modifier = modifier.fillMaxWidth()
        ){
            Row(modifier = modifier, verticalAlignment = Alignment.Top) {
                RoundedAvatarMeetings(placeholderImage = placeholderImage, avatarUrl = community.avatarUrl)
                Spacer(modifier = modifier.width(MeetTheme.sizes.sizeX12))
                Column {
                    BaseText(text = community.nameGroup,
                        textColor = MeetTheme.colors.neutralActive,
                        textStyle = MeetTheme.typography.bodyText1)
                    Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX2))
                    BaseText(text = textNumberPeople,
                        textColor = MeetTheme.colors.neutralDisabled,
                        textStyle = MeetTheme.typography.metadata1)
                    Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX12))
                }
            }
        }
    }
}

fun formatNumberWithSpaces(number: Int): String {
    return "%,d".format(number).replace(',', ' ')
}