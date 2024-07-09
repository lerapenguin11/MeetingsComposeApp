package com.example.composeprotject.screen.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composeprotject.R
import com.example.composeprotject.navigation.BottomNavItem
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SplashScreenViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel,
    mainViewModel: MainViewModel,
    navController: NavController,
    contentPadding: PaddingValues
) {
    mainViewModel.setCurrentScreenShowTopBar(false)
    val isLoading by viewModel.isLoading.collectAsState()
    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            LottieAnimation(
                composition = composition,
                isPlaying = true,
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever
            )
        } else {
            if (isUserLoggedIn) {
                navController.navigate(route = BottomNavItem.Event.route)
            } else {
                navController.navigate(/*TODO: добавить route регистрации*/ route = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        LottieAnimation(composition = composition)
        println("TEST $composition")
    }
}