package com.example.swvlclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.ui.navigation.authGraphRoute
import com.example.swvlclone.ui.navigation.homeGraphRoute
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var googleAuthUiClient: AuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SwvlCloneTheme {
                val startDestination = if (googleAuthUiClient.getSignedInUser() != null) {
                    homeGraphRoute
                } else {
                    authGraphRoute
                }
                SwvlCloneApp(
                    googleAuthUiClient = googleAuthUiClient,
                    startDestination = startDestination,
                )
            }
        }
    }
}

