package com.example.composeprotject.screen.navScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun CommunityScreen(modifier: Modifier = Modifier, viewModel: MainViewModel){
    //viewModel.setCurrentScreen(BottomNavItems.Community)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Community", style = MaterialTheme.typography.bodyLarge)
    }
}