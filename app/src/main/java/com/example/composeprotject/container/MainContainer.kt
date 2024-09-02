package com.example.composeprotject.container

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav.MainGraph
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.topBar.SearchBar

@Composable
fun MainContainer() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = Color.White,
        contentWindowInsets = WindowInsets.statusBars,
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
        MainGraph(
            navController = rememberNavController(),
            contentPadding = contentPadding
        )
    }
}