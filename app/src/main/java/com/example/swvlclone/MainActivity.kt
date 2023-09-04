package com.example.swvlclone

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.swvlclone.ui.SplashScreen
import com.example.swvlclone.ui.navigation.SwvlCloneNavHost
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.delay
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SwvlCloneApp()
        }
    }
}


@Composable
fun SwvlCloneApp() {
    SwvlCloneTheme {
        val navController = rememberNavController()
        var shouldShowSplash by rememberSaveable {
            mutableStateOf(true)
        }
        LaunchedEffect(key1 = Unit) {
            delay(4000)
            shouldShowSplash = false
        }
        if (shouldShowSplash) {
            SplashScreen()
        } else {
            Box(modifier = Modifier
                .safeDrawingPadding()
            ) {
                SwvlCloneNavHost(
                    navController = navController,
                )
            }
        }
    }
}
