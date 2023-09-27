package com.example.swvlclone.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.availabletrips.navigation.TripsDest
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.location.LocationRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.ui.navigation.TimeArgType
import com.google.gson.Gson
import timber.log.Timber

object LocationDest : SwvlCloneDestination {
    override val name: String
        get() = "Location"
    override val route: String
        get() = "location"
    const val tripTimeArg = "trip_time"
    val routeWithArgs = "${route}?$tripTimeArg={${tripTimeArg}}"
    override val arguments = listOf(
        navArgument(name = tripTimeArg) {
            type = TimeArgType()
            defaultValue = TripTime(145, 2563)
        },
    )
}

fun NavGraphBuilder.locationScreen(
    onBackPressed: () -> Unit,
    onLocationPicked: (TripLocation) -> Unit,
) {
    composable(
        route = LocationDest.routeWithArgs,
        arguments = LocationDest.arguments,
    ) { entry ->
        val tripTime = entry.arguments?.getString(TripsDest.tripTimeArg).let {
            Gson().fromJson(it, TripTime::class.java)
        }
        Timber.d("TripTime: ${tripTime.day}: ${tripTime.hour}")

        LocationRoute(
            onBackPressed = onBackPressed,
            onLocationPicked = onLocationPicked
        )
    }
}

fun NavController.navigateToLocation(tripTime: TripTime) {
    this.navigate("${LocationDest.route}?${LocationDest.tripTimeArg}=$tripTime")
}

fun NavController.navigateToLocation() {
    this.navigate(LocationDest.route)
}