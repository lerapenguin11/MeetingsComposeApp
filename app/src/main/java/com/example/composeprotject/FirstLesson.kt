package com.example.composeprotject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.avatar.ProfileAvatarContainer
import com.example.composeprotject.ui.component.avatar.RoundedAvatarMeetings
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.button.OutlinedButton
import com.example.composeprotject.ui.component.button.TextButton
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.input.CustomSearchOutlinedTextField
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.text.TypographyStyleText
import com.example.composeprotject.ui.component.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.component.variant.avatar.AvatarState
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun FirstLesson(){
    Column(
        modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        UIKitButtons()
        UIKitTypography()
        UIKitAvatars()
        CustomSearchOutlinedTextField(textPlaceholder = "Placeholder" , isEnabled = true)
        UIKitChips()
    }
}

@Composable
fun UIKitChips(){
    val textChipList = listOf(
        "Python",
        "Junior",
        "Moscow"
    )
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        textChipList.forEach {
            Chip(text = it)
        }
    }
}

@Composable
fun UIKitButtons() {
    for (state in ButtonState.entries){
        Row(
            Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            if (state == ButtonState.DISABLED){
                FilledButton(onClick = { /*TODO*/}, state = ButtonState.DISABLED)
                OutlinedButton(onClick = { /*TODO*/ }, state = ButtonState.DISABLED)
                TextButton(onClick = { /*TODO*/ }, state = ButtonState.DISABLED)
            }else{
                FilledButton(onClick = { /*TODO*/ })
                OutlinedButton(onClick = { /*TODO*/ })
                TextButton(onClick = { /*TODO*/ })
            }
        }
    }
}

@Composable
fun UIKitTypography(modifier: Modifier = Modifier){
    val typographyList = listOf(
        TypographyStyleText(
            title = "Heading 1",
            subtitle = "SF Pro Display/32/Bold",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.heading1
        ),
        TypographyStyleText(
            title = "Heading 2",
            subtitle = "SF Pro Display24/Bold",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.heading2
        ),
        TypographyStyleText(
            title = "Subheading 1",
            subtitle = "SF Pro Display18/SemiBold",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.subheading1
        ),
        TypographyStyleText(
            title = "Subheading 2",
            subtitle = "SF Pro Display16/SemiBold",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.subheading2
        ),
        TypographyStyleText(
            title = "Body Text 1",
            subtitle = "SF Pro Display/14/SemiBold",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.bodyText1
        ),
        TypographyStyleText(
            title = "Body Text 2",
            subtitle = "SF Pro Display14/Regular",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.bodyText2
        ),
        TypographyStyleText(
            title = "Metadata 1",
            subtitle = "SF Pro Display12/Regular",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.metadata1
        ),
        TypographyStyleText(
            title = "Metadata 2",
            subtitle = "SF Pro Display10/Regular",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.metadata2

        ),
        TypographyStyleText(
            title = "Metadata 3",
            subtitle = "SF Pro Display10/SemiBold",
            titleTextStyle = MeetTheme.typography.subheading1,
            subtitleTextStyle = MeetTheme.typography.bodyText2,
            textStyle = MeetTheme.typography.metadata3
        )
    )
    typographyList.forEach {
        TypographyItem(
            title = it.title,
            subtitle = it.subtitle,
            titleTextStyle = it.titleTextStyle,
            subtitleTextStyle = it.subtitleTextStyle,
            textStyle = it.textStyle,
            modifier = modifier
        )
    }
}

@Composable
fun TypographyItem(
    title : String,
    subtitle : String,
    titleTextStyle : TextStyle,
    subtitleTextStyle : TextStyle,
    subtitleTextColor : Color = MeetTheme.colors.neutralDisabled,
    textStyle : TextStyle,
    modifier: Modifier
){
    LazyRow(modifier = modifier
        .sizeIn(minHeight = 54.dp)
        .fillMaxWidth()
        .padding(top = 10.dp)
    ) {
        item {
            Column(
                modifier
                    .width(200.dp)
                    .padding(end = 20.dp)) {
                Box(modifier.padding(bottom = 4.dp)) {
                    BaseText(textStyle = titleTextStyle, text = title, modifier = modifier)
                }
                BaseText(
                    textColor = subtitleTextColor,
                    textStyle = subtitleTextStyle,
                    text = subtitle, modifier = modifier)
            }
        }
        item {
            Row(Modifier) {
                BaseText(textColor = MeetTheme.colors.neutralDark, textStyle = textStyle,
                    text = stringResource(id = R.string.text), modifier = modifier)
            }
        }
    }
}

@Composable
fun UIKitAvatars(modifier: Modifier = Modifier) {
    Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        RoundedAvatarMeetings(
            placeholderImage = R.drawable.ic_avatar_meetings,
            avatarUrl = "https://fikiwiki.com/uploads/posts/2022-02/1644881323_42-fikiwiki-com-p-krasivie-kartinki-pingvinov-49.jpg")
        Box(contentAlignment = Alignment.Center, modifier = modifier.padding(horizontal = 10.dp)) {
            ProfileAvatarContainer(
                colorContainer = MeetTheme.colors.neutralLine,
                avatarUrl = null,
                variant = ProfileAvatarVariant.TINY,
                contentDescription = R.string.text_content_description,
                state = AvatarState.DISPLAY)
        }
    }
}