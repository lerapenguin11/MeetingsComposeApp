package com.example.composeprotject.screen_old.splashScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composeprotject.R
import com.example.composeprotject.viewModel_old.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashScreenViewModel: SplashScreenViewModel = koinViewModel(),
    contentPadding: PaddingValues
) {
    val isLoading by splashScreenViewModel.getIsLoadingFlow().collectAsState()
    val isUserLoggedIn by splashScreenViewModel.getIsUserLoggedIn().collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        when (isLoading) {
            true -> {
                LottieAnimation(
                    composition = composition,
                    isPlaying = true,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
            }

            false -> {
                val condition = false //TODO: isUserLoggedIn
            }
        }
    }
}