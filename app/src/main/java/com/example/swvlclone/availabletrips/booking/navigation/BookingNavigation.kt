package com.example.swvlclone.availabletrips.booking.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.availabletrips.booking.BookingRoute
import com.example.swvlclone.availabletrips.tripitem.components.TripModel

const val BOOKING_ROUTE = "booking_screen"
fun NavGraphBuilder.bookingScreen(
    onBackPressed: () -> Unit,
) {
    composable(route = BOOKING_ROUTE) {
        BookingRoute(onBackPressed = onBackPressed)
    }
}

fun NavController.navigateToBooking(tripModel: TripModel) {
    navigate(BOOKING_ROUTE)
}