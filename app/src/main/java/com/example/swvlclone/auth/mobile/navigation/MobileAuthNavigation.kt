package com.example.swvlclone.auth.mobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.mobile.MobileAuthRoute
import com.example.swvlclone.ui.navigation.MobileAuthDest

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

