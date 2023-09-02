package com.example.swvlclone.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.location.LocationRoute
import com.example.swvlclone.ui.navigation.LocationDest

fun NavGraphBuilder.locationScreen(
    onBackPressed: () -> Unit,
    onLocationPicked: (TripLocation) -> Unit,
) {
    composable(
        route = LocationDest.routeWithArgs,
        arguments = LocationDest.arguments
    ) {
        LocationRoute(
            onBackPressed = onBackPressed,
            onLocationPicked = onLocationPicked
        )
    }
}

fun NavController.navigateToLocation(tripTime: TripTime) {
    this.navigate("${LocationDest.route}/$tripTime")
}