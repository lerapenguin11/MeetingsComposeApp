package com.example.composeprotject.ui.component.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.network.networkStyle.SocialNetworkStyleDefault
import com.example.composeprotject.ui.component.network.networkStyle.SocialNetworkVariantStyle
import com.example.composeprotject.ui.component.network.variant.SocialNetworkVariant
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun SocialNetwork(
    variant: SocialNetworkVariant,
    modifier: Modifier = Modifier,
    style: SocialNetworkVariantStyle = SocialNetworkStyleDefault.style(),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .size(size = 52.dp),
        onClick = { onClick() },
        enabled = true,
        shape = RoundedCornerShape(size = MeetTheme.sizes.sizeX16),
        colors = ButtonDefaults.buttonColors(
            containerColor = MeetTheme.colors.primary,
            contentColor = MeetTheme.colors.neutralWhite
        ),
        contentPadding = PaddingValues(
            top = style.contentTopPadding(variant = variant),
            start = style.contentStartPadding(variant = variant),
            end = style.contentEndPadding(variant = variant),
            bottom = style.contentBottomPadding(variant = variant)
        )
    ) {
        Image(
            modifier = Modifier.size(
                width = style.widthImage(variant = variant),
                height = style.heightImage(variant = variant)
            ),
            painter = painterResource(id = style.imageVariant(variant = variant)),
            contentDescription = stringResource(R.string.text_social_network)
        )
    }
}