package com.example.swvlclone.availabletrips.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.availabletrips.AvailableTripsRoute
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.ui.navigation.LocationArgType
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.ui.navigation.TimeArgType
import com.google.gson.Gson
import timber.log.Timber

object TripsDest : SwvlCloneDestination {
    override val name: String
        get() = "Trips"
    override val route: String
        get() = "available_trips_screen"
    const val tripTimeArg = "trip_time"
    const val tripLocationArg = "trip_location"
    val routeWithArgs = "${route}/{$tripTimeArg}/{$tripLocationArg}"
    override val arguments = listOf(
        navArgument(name = tripTimeArg) {
            type = TimeArgType()
        },
        navArgument(name = tripLocationArg) {
            type = LocationArgType()
        }
    )
}
fun NavGraphBuilder.availableTripsScreen(
    onBackPressed: () -> Unit,
    onTripItemClick: (TripModel) -> Unit,
) {
    composable(
        route = TripsDest.routeWithArgs,
        arguments = TripsDest.arguments
    ) { entry ->

        val tripTime = entry.arguments?.getString(TripsDest.tripTimeArg).let {
            Gson().fromJson(it, TripTime::class.java)
        }
        val tripLocation = entry.arguments?.getString(TripsDest.tripLocationArg).let {
            Gson().fromJson(it, TripLocation::class.java)
        }
        LaunchedEffect(key1 = Unit) {
            Timber.i("Trip Time: ${tripTime.day}, ${tripTime.hour}")
            Timber.i("Trip Location: ${tripLocation.from}, ${tripLocation.to}")
        }
        AvailableTripsRoute(
            selectedTripTime = tripTime,
            selectedTripLocation = tripLocation,
            onBackPressed = onBackPressed,
            onTripItemClick = onTripItemClick,
        )
    }
}

fun NavController.navigateToAvailableTrips(
    tripTime: TripTime,
    tripLocation: TripLocation
) {
    this.navigate("${TripsDest.route}/${tripTime}/${tripLocation}")
}