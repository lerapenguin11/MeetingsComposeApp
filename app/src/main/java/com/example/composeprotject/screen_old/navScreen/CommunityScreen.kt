package com.example.composeprotject.screen_old.navScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composeprotject.R
import com.example.composeprotject.ui.component_old.card.CommunitiesCard
import com.example.composeprotject.ui.component_old.input.CustomSearchOutlinedTextFieldIconOld
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel_old.nav.CommunityViewModel
import com.example.domain.old.model.Community
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    onCommunityClick: (Community) -> Unit,
    communityViewModel: CommunityViewModel = koinViewModel()
) {
    val communityList by communityViewModel.getCommunitiesFlow().collectAsState()
    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        CustomSearchOutlinedTextFieldIconOld(
            textPlaceholder = stringResource(id = R.string.text_placeholder_input_search),
            isEnabled = true
        )
        LazyColumn {
            items(communityList) { community ->
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