package com.example.swvlclone.usertrips.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.usertrips.details.TripDetailsRoute

object TripDetailsDest : SwvlCloneDestination {
    override val route: String
        get() = "trip_details_screen"
    override val name: String
        get() = ""
}


fun NavGraphBuilder.tripDetailsScreen(
    onBackPressed: () -> Unit,
) {
    composable(TripDetailsDest.route) {

        TripDetailsRoute(
            onBackPressed = onBackPressed,
            tripModel =
            TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            )
        )
    }
}

fun NavController.navigateToTripDetails(tripModel: TripModel?) {
    navigate(TripDetailsDest.route)
}