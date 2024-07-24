package com.example.composeprotject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav.RootNavigationGraph
import com.example.composeprotject.ui.theme.MeetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeetTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MeetTheme.colors.neutralWhite,
                ) { contentPadding ->
                    RootNavigationGraph(
                        navController = navController,
                        isLoggedIn = false,
                        contentPadding = contentPadding
                    )
                }
            } //TODO: добавить topAppBar на AuthGraph
        }
    }
}











