package com.example.swvlclone.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.MobileAuthRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination


object MobileAuthDest : SwvlCloneDestination {
    override val route: String
        get() = "mobile_auth_screen"
    override val name: String
        get() = "Mobile Auth"
}


fun NavGraphBuilder.mobileAuthScreen(
    onBackPressed: () -> Unit,
    onForwardPressed: (String) -> Unit
) {
    composable(route = MobileAuthDest.route) {
        MobileAuthRoute(
            onBackPressed = onBackPressed,
            onForwardButtonClick = { onForwardPressed(it) }
        )
    }
}

fun NavController.navigateToMobileAuth() {
    this.navigate(MobileAuthDest.route)
}

