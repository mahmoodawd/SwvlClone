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
import com.example.swvlclone.availabletrips.booking.navigation.bookingScreen
import com.example.swvlclone.availabletrips.booking.navigation.navigateToBooking
import com.example.swvlclone.availabletrips.navigation.availableTripsScreen
import com.example.swvlclone.availabletrips.navigation.navigateToAvailableTrips
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.home.navigation.homeScreen
import com.example.swvlclone.home.navigation.navigateToHome
import com.example.swvlclone.location.navigation.locationScreen
import com.example.swvlclone.location.navigation.navigateToLocation
import com.example.swvlclone.settings.navigation.cityScreen
import com.example.swvlclone.settings.navigation.languageScreen
import com.example.swvlclone.settings.navigation.navigateToCityChange
import com.example.swvlclone.settings.navigation.navigateToLanguageChange
import com.example.swvlclone.settings.navigation.navigateToSocialAccounts
import com.example.swvlclone.settings.navigation.settingsScreen
import com.example.swvlclone.settings.navigation.socialAccountsScreen
import com.example.swvlclone.usertrips.navigation.UserTripsDest
import com.example.swvlclone.usertrips.navigation.navigateToTripDetails
import com.example.swvlclone.usertrips.navigation.tripDetailsScreen
import com.example.swvlclone.usertrips.navigation.userTripsScreen

@Composable
fun SwvlCloneNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    NavHost(
        navController = navController,
        startDestination = HomeDest.route,
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
            currentDestinationRoute = currentDestination?.route ?: UserTripsDest.route,
            onLocationClick = { tripTime ->
                navController.navigateToLocation(tripTime)
            },
            onDrawerItemClick = { route -> navController.navigate(route) }
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
        //TODO put in a separate nested navigation
        availableTripsScreen(
            onBackPressed = { navController.popBackStack() },
            onTripItemClick = { navController.navigateToBooking(it) }
        )
        bookingScreen(
            onBackPressed = { navController.popBackStack() }
        )

        userTripsScreen(
            onBackPressed = { navController.popBackStack() },
            onItemClick = { navController.navigateToTripDetails(it) }
        )
        tripDetailsScreen(
            onBackPressed = { navController.popBackStack() }
        )

        settingsScreen(
            onBackPressed = { navController.popBackStack() },
            onCityClick = {
                navController.navigateToCityChange(it)
            },
            onLanguageClick = {
                navController.navigateToLanguageChange(it)
            },
            onConnectedAccountsClick = {
                navController.navigateToSocialAccounts(ArrayList(it))
            },
        )
        cityScreen(onBackPressed = { navController.popBackStack() })
        languageScreen(onBackPressed = { navController.popBackStack() })
        socialAccountsScreen(onBackPressed = { navController.popBackStack() })


    }
}

