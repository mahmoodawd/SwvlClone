package com.example.swvlclone.availabletrips.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.availabletrips.AvailableTripsRoute
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.ui.navigation.TripsDest
import com.google.gson.Gson
import timber.log.Timber

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