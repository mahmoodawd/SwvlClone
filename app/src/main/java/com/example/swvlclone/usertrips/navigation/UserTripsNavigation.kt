package com.example.swvlclone.usertrips.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.usertrips.UserTripsRoute

object UserTripsDest : SwvlCloneDestination {
    override val route: String
        get() = "your_trips_screen"
    override val name: String
        get() = "Your Trips"
}

fun NavGraphBuilder.userTripsScreen(
    onBackPressed: () -> Unit,
    onItemClick: (TripModel) -> Unit
) {
    composable(route = UserTripsDest.route) {
        UserTripsRoute(onBackPressed = onBackPressed, onItemClick = onItemClick)
    }
}
