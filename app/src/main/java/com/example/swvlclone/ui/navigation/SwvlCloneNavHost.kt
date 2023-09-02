package com.example.swvlclone.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.auth.AuthScreen
import com.example.swvlclone.auth.MobileAuthScreen
import com.example.swvlclone.auth.OTPScreen
import com.example.swvlclone.availabletrips.TripsScreen
import com.example.swvlclone.home.HomeScreen
import com.example.swvlclone.location.LocationScreen
import com.google.gson.Gson
import timber.log.Timber

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

            composable(route = AuthDest.route) {
                AuthScreen(onPhoneFieldClick = {
                    navController.navigate(MobileAuthDest.route)
                })
            }

            composable(route = MobileAuthDest.route) {
                MobileAuthScreen(onForwardButtonClick = { phoneNumber ->
                    navController.navigateToOtpScreen(phoneNumber)
                    Timber.i("ForwardButton Pressed")
                },
                    onBackPressed = {
                        navController.popBackStack()
                    })
            }

            composable(route = OtpDest.routeWithArgs) {
                val number = it.arguments?.getString(OtpDest.mobileNumberArg)
                OTPScreen(
                    phoneNumber = number!!,
                    onForwardClick = { navController.navigate(HomeDest.route){
                        popUpTo("auth")
                    } },
                    onBackPressed = { navController.popBackStack() })
            }
        }

        composable(route = HomeDest.route) { entry ->
            val context = LocalContext.current


            HomeScreen(
                currentDestinationRoute = currentDestination?.route,
                onLocationClick = { tripTime ->
                    navController.navigateToLocationScreen(tripTime)
                },
                onDrawerItemClick = {
                    Toast.makeText(
                        context,
                        it,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }

        composable(
            route = LocationDest.routeWithArgs,
            arguments = LocationDest.arguments
        ) { entry ->

            val tripTime = entry.arguments?.getString(LocationDest.tripTimeArg)?.let {
                Gson().fromJson(it, TripTime::class.java)
            }
            LocationScreen(
                onBackPressed = { navController.popBackStack() },
                onLocationPicked = { pickedLocation ->
                    navController.navigateToTripsScreen(tripTime!!, pickedLocation)
                }
            )
        }

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

            TripsScreen(
                selectedTripTime = tripTime,
                selectedTripLocation = tripLocation,
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}

private fun NavHostController.navigateToOtpScreen(phoneNumber: String) {
    this.navigate("${OtpDest.route}/$phoneNumber")
}

private fun NavHostController.navigateToLocationScreen(tripTime: TripTime) {
    this.navigate("${LocationDest.route}/$tripTime")
}

private fun NavHostController.navigateToTripsScreen(
    tripTime: TripTime,
    tripLocation: TripLocation
) {
    this.navigate("${TripsDest.route}/${tripTime}/${tripLocation}")
}