package com.example.swvlclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.example.swvlclone.auth.main.navigation.authScreen
import com.example.swvlclone.auth.mobile.navigation.mobileAuthScreen
import com.example.swvlclone.auth.mobile.navigation.navigateToMobileAuth
import com.example.swvlclone.auth.mobile.otp.navigation.navigateToOtp
import com.example.swvlclone.auth.mobile.otp.navigation.otpScreen
import com.example.swvlclone.availabletrips.navigation.availableTripsScreen
import com.example.swvlclone.availabletrips.navigation.navigateToAvailableTrips
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.home.navigation.homeScreen
import com.example.swvlclone.home.navigation.navigateToHome
import com.example.swvlclone.location.navigation.locationScreen
import com.example.swvlclone.location.navigation.navigateToLocation

@Composable
fun SwvlCloneNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    NavHost(
        navController = navController,
        startDestination = "auth",
        modifier = modifier
    ) {
        navigation(route = "auth", startDestination = AuthDest.route) {

            authScreen(
                onPhoneFieldClick = { navController.navigateToMobileAuth() }
            )

            mobileAuthScreen(
                onForwardPressed = { phoneNumber ->
                    navController.navigateToOtp(phoneNumber)
                },
                onBackPressed = { navController.popBackStack() }
            )

            otpScreen(
                onForwardPressed = { navController.navigateToHome() },
                onBackPressed = { navController.popBackStack() })
        }

        homeScreen(
            currentDestinationRoute = currentDestination?.route ?: YourTripsDest.route,
            onLocationClick = { tripTime ->
                navController.navigateToLocation(tripTime)
            },
            onDrawerItemClick = { /*route -> navController.navigate(route)*/ }
        )

        locationScreen(
            onBackPressed = { navController.popBackStack() },
            onLocationPicked = { pickedLocation ->
                navController.navigateToAvailableTrips(
                    TripTime("", ""),
                    pickedLocation
                )
            }
        )

        availableTripsScreen(
            onBackPressed = { navController.popBackStack() }
        )

    }
}

