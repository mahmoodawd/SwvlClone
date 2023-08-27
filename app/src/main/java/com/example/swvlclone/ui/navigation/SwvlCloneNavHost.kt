package com.example.swvlclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swvlclone.ui.auth.AuthScreen
import com.example.swvlclone.ui.auth.MobileAuthScreen
import com.example.swvlclone.ui.auth.OTPScreen
import com.example.swvlclone.ui.home.HomeScreen
import timber.log.Timber

@Composable
fun SwvlCloneNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeDest.route,
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
            HomeScreen()
        }
    }
}

private fun NavHostController.navigateToOtpScreen(phoneNumber: String) {
    this.navigate("${OtpDest.route}/$phoneNumber")
}