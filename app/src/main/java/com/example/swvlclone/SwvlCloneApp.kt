package com.example.swvlclone

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
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