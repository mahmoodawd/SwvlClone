package com.example.swvlclone

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.ui.navigation.SwvlCloneNavHost

@Composable
fun SwvlCloneApp(
    appState: SwvlCloneAppState = rememberAppState(),
    googleAuthUiClient: AuthUiClient,
    startDestination: String
) {


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(
                hostState = appState.snackbarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.scrim
                    )
                }
            )
        }
    ) { innerPaddingValues ->
        SwvlCloneNavHost(
            appState = appState,
            googleAuthUiClient = googleAuthUiClient,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPaddingValues)
        )
    }
}