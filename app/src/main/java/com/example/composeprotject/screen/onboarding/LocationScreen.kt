package com.example.composeprotject.screen.onboarding

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.LocationOnboardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    locationOnboardingViewModel: LocationOnboardingViewModel = koinViewModel(),
    onMovingNextGraph: (String) -> Unit
) {
    val startDestination by locationOnboardingViewModel.getStartDestination()
        .collectAsStateWithLifecycle()
    val context = LocalContext.current
    var locationPermissionGranted: Boolean? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            locationPermissionGranted = isGranted
        }
    )

    LaunchedEffect(key1 = Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    Box(
        modifier = with(modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = CommonDrawables.ic_location_map),
                    contentScale = ContentScale.FillBounds
                )
                .padding(contentPadding)
                .padding(
                    horizontal = MeetTheme.sizes.sizeX16,
                    vertical = MeetTheme.sizes.sizeX40
                )
        },
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onMovingNextGraph(startDestination)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MeetTheme.colors.secondary
            ),
            shape = RoundedCornerShape(MeetTheme.sizes.sizeX16),
            contentPadding = PaddingValues(
                horizontal = MeetTheme.sizes.sizeX16,
                vertical = MeetTheme.sizes.sizeX16
            )
        ) {
            Text(
                text = stringResource(id = CommonString.text_continue),
                style = MeetTheme.typography.interSemiBold18,
                color = MeetTheme.colors.primary
            )
        }
    }
}