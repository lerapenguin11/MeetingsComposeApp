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
fun StillScreen(modifier: Modifier = Modifier, viewModel: MainViewModel){
    //viewModel.setCurrentScreen(BottomNavItems.Still)
    viewModel.setCurrentScreenShowTopBar(true)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Still", style = MaterialTheme.typography.bodyLarge)
    }
}