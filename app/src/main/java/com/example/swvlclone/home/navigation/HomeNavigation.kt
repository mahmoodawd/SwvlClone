package com.example.swvlclone.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.domain.models.TripLocation
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
    onOfferClick: () -> Unit,
    onTripCategoryClick: () -> Unit,
    onCityClick: () -> Unit,
    googleAuthUiClient: AuthUiClient,
    onFavoriteLocationClick: (Boolean, String, TripLocation, TripTime) -> Unit,
) {
    composable(route = HomeDest.route) {
        HomeRoute(
            currentDestinationRoute = currentDestinationRoute,
            onLocationClick = onLocationClick,
            onDrawerItemClick = onDrawerItemClick,
            onOfferClick = onOfferClick,
            onFavoriteLocationClick = onFavoriteLocationClick,
            onTripCategoryClick = onTripCategoryClick,
            onCityClick = onCityClick,
            googleAuthUiClient = googleAuthUiClient,
        )
    }
}

fun NavController.navigateToHome() {
    navigate(HomeDest.route) {
        popUpTo(authGraphRoute)
    }
}