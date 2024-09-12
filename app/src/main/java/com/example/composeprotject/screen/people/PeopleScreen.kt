package com.example.composeprotject.screen.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.screen.state.PeopleState
import com.example.composeprotject.ui.component.person.Person
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.PeopleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PeopleScreen(
    id: Int,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    peopleViewModel: PeopleViewModel = koinViewModel(),
    screenState: PeopleState
) {
    LaunchedEffect(Unit) {
        when (screenState) {
            PeopleState.EVENT_PEOPLE -> {
                peopleViewModel.loadPeopleByEventId(eventId = id)
            }

            PeopleState.SUBSCRIBERS -> {
                peopleViewModel.loadPeopleByCategoryId(communityId = id)
            }
        }
    }

    val people by peopleViewModel.getPeople().collectAsStateWithLifecycle()

    LazyVerticalGrid(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16),
        contentPadding = PaddingValues(top = MeetTheme.sizes.sizeX32, bottom = 28.dp),
        columns = GridCells.Fixed(count = NUMBER_COLUMNS),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(people) { item ->
            Person(
                namePerson = item.name,
                avatarUrl = item.image,
                tags = item.interests
            )
        }
    }
}

private const val NUMBER_COLUMNS = 3