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
import com.example.swvlclone.ui.auth.AuthScreen
import com.example.swvlclone.ui.auth.MobileAuthScreen
import com.example.swvlclone.ui.auth.OTPScreen
import com.example.swvlclone.ui.home.HomeScreen
import com.example.swvlclone.ui.location.LocationScreen
import com.example.swvlclone.ui.trips.TripsScreen
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
        startDestination = TripsDest.route,
        modifier = modifier
    ) {

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
                onForwardClick = { navController.navigate(HomeDest.route) },
                onBackPressed = { navController.popBackStack() })
        }
        composable(route = HomeDest.route) {
            val context = LocalContext.current
            HomeScreen(
                currentDestinationRoute = currentDestination?.route,
                onLocationClick = { navController.navigate(LocationDest.route) },
                onDrawerItemClick = {
                    Toast.makeText(
                        context,
                        it,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
        composable(LocationDest.route) {
            LocationScreen(
                onBackPressed = { navController.popBackStack() },
                onLocationPicked = { navController.navigate(TripsDest.route) }
            )
        }
        composable(route = TripsDest.route) {
            TripsScreen()
        }
    }
}

private fun NavHostController.navigateToOtpScreen(phoneNumber: String) {
    this.navigate("${OtpDest.route}/$phoneNumber")
}