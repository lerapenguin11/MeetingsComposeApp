package com.example.composeprotject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav.RootNavigationGraph
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.SplashViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {
    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MeetTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White
                ) { contentPadding ->
                    val startDestination by viewModel.getStartDestination()
                    RootNavigationGraph(
                        navHostController = rememberNavController(),
                        startDestination = startDestination,
                        contentPadding = contentPadding,
                        isLoading = true
                    )
                }
            }
        }
    }
}











