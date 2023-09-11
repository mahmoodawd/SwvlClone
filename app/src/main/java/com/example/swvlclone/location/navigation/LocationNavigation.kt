package com.example.swvlclone.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.location.LocationRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.ui.navigation.TimeArgType

object LocationDest : SwvlCloneDestination {
    override val name: String
        get() = "Location"
    override val route: String
        get() = "location"
    const val tripTimeArg = "trip_time"
    val routeWithArgs = "$route/{$tripTimeArg}"
    override val arguments = listOf(
        navArgument(name = tripTimeArg) {
            type = TimeArgType()
        },
    )
}

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