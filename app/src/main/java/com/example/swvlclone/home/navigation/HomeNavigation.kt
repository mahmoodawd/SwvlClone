package com.example.swvlclone.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.home.HomeRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.ui.navigation.authGraphRoute


object HomeDest : SwvlCloneDestination {
    override val route: String
        get() = "home_screen"

}

fun NavGraphBuilder.homeScreen(
    currentDestinationRoute: String,
    onLocationClick: (TripTime) -> Unit,
    onDrawerItemClick: (String) -> Unit,
) {
    composable(route = HomeDest.route) {
        HomeRoute(
            currentDestinationRoute = currentDestinationRoute,
            onLocationClick = onLocationClick,
            onDrawerItemClick = onDrawerItemClick
        )
    }
}

fun NavController.navigateToHome() {
    navigate(HomeDest.route) {
        popUpTo(authGraphRoute)
    }
}