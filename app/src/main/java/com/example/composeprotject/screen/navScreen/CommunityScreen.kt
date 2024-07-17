package com.example.composeprotject.screen.navScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.domain.model.Community
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.card.CommunitiesCard
import com.example.composeprotject.ui.component.input.CustomSearchOutlinedTextFieldIcon
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    contentPadding: PaddingValues,
    navController: NavHostController,
    onCommunityClick: (Community) -> Unit
) {
    viewModel.setCurrentScreen(
        screen = NavItem.CommunityItem,
        showTopBar = true,
        showBottomBar = true
    )
    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
        CustomSearchOutlinedTextFieldIcon(
            textPlaceholder = stringResource(id = R.string.text_placeholder_input_search),
            isEnabled = true
        )
        LazyColumn {
            items(communityList()) { community ->
                CommunitiesCard(
                    placeholderImage = R.drawable.ic_placeholder_community,
                    community = community,
                    onClick = {
                        onCommunityClick(community)
                    }
                )
            }
        }
    }
}

fun communityList() = listOf<Community>(
    Community(
        id = 0,
        avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
        nameGroup = "Designa",
        numberPeople = 1000000
    ),
    Community(
        id = 1,
        avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
        nameGroup = "Designa",
        numberPeople = 1000000
    ),
    Community(
        id = 2,
        avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
        nameGroup = "Designa",
        numberPeople = 1000000
    ),
    Community(
        id = 3,
        avatarUrl = "https://dm-centre.ru/wp-content/uploads/2023/09/kub33.jpg",
        nameGroup = "Designa",
        numberPeople = 1000000
    )
)