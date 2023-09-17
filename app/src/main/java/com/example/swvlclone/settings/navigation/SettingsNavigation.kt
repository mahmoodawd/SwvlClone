package com.example.swvlclone.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.settings.SettingsRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object SettingsDest : SwvlCloneDestination {
    override val route: String
        get() = "settings_screen"
    override val name: String
        get() = "Settings"
}

fun NavGraphBuilder.settingsScreen(
    onBackPressed: () -> Unit,
    onCityClick: (String) -> Unit,
    onLanguageClick: (String) -> Unit,
    onConnectedAccountsClick: (List<String>) -> Unit,
    googleAuthUiClient: AuthUiClient,
    onSignOut: () -> Unit,
) {
    composable(route = SettingsDest.route) {
        SettingsRoute(
            onBackPressed = onBackPressed,
            onCityClick = onCityClick,
            onLanguageClick = onLanguageClick,
            onConnectedAccountsClick = onConnectedAccountsClick,
            googleAuthUiClient = googleAuthUiClient,
            onSignOut = onSignOut
        )
    }
}

fun NavController.navigateToSettings() {
    navigate(SettingsDest.route)
}