package com.example.composeprotject.container

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeprotject.screen.home.HomeScreen
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.topBar.SearchBar

@Composable
fun MainContainer(function: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            SearchBar(
                isEnabled = true,
                state = InputState.SUCCESS,
                onValueChange = {
                    /*TODO*/
                }
            )
        }
    ) { contentPadding ->
        HomeScreen(contentPadding = contentPadding)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContainerTest() {
    MainContainer {

    }
}