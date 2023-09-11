package com.example.swvlclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.example.swvlclone.auth.main.navigation.AuthDest
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
import com.example.swvlclone.home.navigation.HomeDest
import com.example.swvlclone.home.navigation.homeScreen
import com.example.swvlclone.home.navigation.navigateToHome
import com.example.swvlclone.location.navigation.LocationDest
import com.example.swvlclone.location.navigation.locationScreen
import com.example.swvlclone.location.navigation.navigateToLocation
import com.example.swvlclone.packages.navigation.PackagesDest
import com.example.swvlclone.packages.navigation.packagesScreen
import com.example.swvlclone.payment.navigation.addCardScreen
import com.example.swvlclone.payment.navigation.navigateToAddCard
import com.example.swvlclone.payment.navigation.paymentScreen
import com.example.swvlclone.payment.navigation.walletScreen
import com.example.swvlclone.privacycenter.help
import com.example.swvlclone.privacycenter.navigation.privacyCenterScreen
import com.example.swvlclone.settings.navigation.SettingsDest
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


const val authGraphRoute = "auth"
const val availableTripsGraphRoute = "available_trips"
const val userTripsGraphRoute = "user_trips"
const val settingsGraphRoute = "settings"
const val paymentGraphRoute = "payment"


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
        navigation(route = authGraphRoute, startDestination = AuthDest.route) {

            authScreen(onPhoneFieldClick = { navController.navigateToMobileAuth() })

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

        navigation(
            route = availableTripsGraphRoute,
            startDestination = LocationDest.route
        ) {
            locationScreen(
                onBackPressed = {
                    navController.popBackStack(
                        route = availableTripsGraphRoute,
                        inclusive = true
                    )
                },
                onLocationPicked = { pickedLocation ->
                    navController.navigateToAvailableTrips(
                        TripTime("", ""),
                        pickedLocation
                    )
                }
            )

            availableTripsScreen(
                onBackPressed = { navController.popBackStack() },
                onTripItemClick = { navController.navigateToBooking(it) }
            )

            bookingScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }

        navigation(route = userTripsGraphRoute, startDestination = UserTripsDest.route) {

            userTripsScreen(
                onBackPressed = {
                    navController.popBackStack(
                        route = userTripsGraphRoute,
                        inclusive = true
                    )
                },
                onItemClick = { navController.navigateToTripDetails(it) }
            )
            tripDetailsScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }

        navigation(route = settingsGraphRoute, startDestination = SettingsDest.route) {

            settingsScreen(
                onBackPressed = {
                    navController.popBackStack(
                        route = settingsGraphRoute,
                        inclusive = true
                    )
                },
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

        navigation(route = paymentGraphRoute, startDestination = PackagesDest.route) {

            paymentScreen(
                onBackPressed = {
                    navController.popBackStack(
                        route = paymentGraphRoute,
                        inclusive = true
                    )
                },
                onAddCreditClick = {
                    navController.navigateToAddCard()
                }
            )
            walletScreen(
                onBackPressed = { navController.popBackStack() },
                onAddCreditClick = {
                    navController.navigateToAddCard()
                }
            )
            addCardScreen(onBackPressed = { navController.popBackStack() })
        }

        packagesScreen(onBackPressed = { navController.popBackStack() })

        privacyCenterScreen(onBackPressed = { navController.popBackStack() })

        help()
    }
}

