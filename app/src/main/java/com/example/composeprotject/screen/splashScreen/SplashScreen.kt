package com.example.composeprotject.screen.splashScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SplashScreenViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashScreenViewModel: SplashScreenViewModel,
    mainViewModel: MainViewModel,
    navController: NavController,
    contentPadding: PaddingValues
) {
    mainViewModel.setCurrentScreen(
        screen = NavItem.SplashScreenItem,
        showTopBar = false,
        showBottomBar = false
    )
    val isLoading by splashScreenViewModel.isLoading.collectAsState()
    val isUserLoggedIn by splashScreenViewModel.isUserLoggedIn.collectAsState()
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
            val condition = false //TODO: isUserLoggedIn

            val route =
                if (condition) NavItem.EventItem.route else NavItem.VerifInputPhoneNumberScreenItem.route
            navController.navigate(route = route)
        }
    }
}