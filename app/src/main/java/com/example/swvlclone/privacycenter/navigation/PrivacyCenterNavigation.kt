package com.example.swvlclone.privacycenter.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.privacycenter.PrivacyCenterRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object PrivacyCenterDest : SwvlCloneDestination {
    override val route: String
        get() = "privacy_center_screen"
    override val name: String
        get() = "Privacy Center"
}

fun NavGraphBuilder.privacyCenterScreen(
    onBackPressed: () -> Unit,
) {
    composable(route = PrivacyCenterDest.route) {
        PrivacyCenterRoute(onBackPressed = onBackPressed)
    }
}