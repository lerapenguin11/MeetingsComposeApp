package com.example.composeprotject.ui.component.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun ChoiceInterestsBanner(
    modifier: Modifier = Modifier,
    onSelectInterests: () -> Unit
) {
    Box(
        modifier = with(modifier) {
            fillMaxWidth()
                .paint(
                    painterResource(id = CommonDrawables.ic_banner),
                    contentScale = ContentScale.FillBounds
                )
                .padding(all = MeetTheme.sizes.sizeX10)
        }
    ) {
        Column {
            Text(
                text = stringResource(CommonString.text_interests_banner),
                color = Color.White,
                style = MeetTheme.typography.interMedium14
            )
            SpacerHeight(height = 14.dp)
            Button(
                modifier = Modifier,
                onClick = { onSelectInterests() },
                shape = RoundedCornerShape(size = MeetTheme.sizes.sizeX8),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = MeetTheme.colors.backgroundColorSecondary,
                            shape = RoundedCornerShape(size = MeetTheme.sizes.sizeX8)
                        )
                        .padding(
                            top = MeetTheme.sizes.sizeX8,
                            bottom = MeetTheme.sizes.sizeX8,
                            start = MeetTheme.sizes.sizeX12,
                            end = MeetTheme.sizes.sizeX12
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(CommonString.text_select_interests),
                        color = MeetTheme.colors.primary,
                        style = MeetTheme.typography.interMedium16
                    )
                }
            }
        }
    }
}