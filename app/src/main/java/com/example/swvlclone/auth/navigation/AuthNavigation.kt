package com.example.swvlclone.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.AuthMainRoute
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object AuthDest : SwvlCloneDestination {
    override val route: String
        get() = "auth_screen"
    override val name: String
        get() = "Auth"
}

fun NavGraphBuilder.authScreen(
    onPhoneFieldClick: () -> Unit,
    onSignInSuccess: () -> Unit,
    googleAuthUiClient: AuthUiClient
) {
    composable(route = AuthDest.route) {
        AuthMainRoute(
            onPhoneFieldClick = onPhoneFieldClick,
            onGoogleSignInSuccess = onSignInSuccess,
            googleAuthUiClient = googleAuthUiClient
        )
    }
}

fun NavController.navigateToAuth() {
    this.navigate(AuthDest.route)
}

