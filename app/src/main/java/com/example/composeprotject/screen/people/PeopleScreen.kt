package com.example.composeprotject.screen.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeprotject.model.interest.Interest
import com.example.composeprotject.ui.component.person.Person
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun PeopleScreen(
    eventId: Int,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16),
        contentPadding = PaddingValues(top = MeetTheme.sizes.sizeX32, bottom = 28.dp),
        columns = GridCells.Fixed(count = NUMBER_COLUMNS),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(200) { item ->
            Person(
                namePerson = "Анастасия"/*item.name*/,
                avatarUrl = "https://images11.domashnyochag.ru/upload/img_cache/e9e/e9e885bc744faf05a6fd0f21962eba77_ce_1159x801x21x0_cropped_1332x888.jpg"/*item.avatarUrl*/,
                tags = listOf(
                    Interest(
                        id = 0,
                        title = "Разработка"
                    )
                )/*item.interests*/
            )
        }
    }
}

private const val NUMBER_COLUMNS = 3