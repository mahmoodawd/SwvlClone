package com.example.swvlclone.auth.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.main.AuthMainRoute
import com.example.swvlclone.ui.navigation.AuthDest

fun NavGraphBuilder.authScreen(onPhoneFieldClick: () -> Unit) {
    composable(route = AuthDest.route) {
        AuthMainRoute(onPhoneFieldClick = { onPhoneFieldClick() })
    }
}

fun NavController.navigateToAuth() {
    this.navigate(AuthDest.route)
}

