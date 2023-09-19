package com.example.swvlclone.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.auth.OtpRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object OtpDest : SwvlCloneDestination {
    override val route: String
        get() = "otp_screen"
    override val name: String
        get() = "OTP"
    const val mobileNumberArg = "mobile_number"
    val routeWithArgs = "$route/{$mobileNumberArg}"
    override val arguments = listOf(
        navArgument(name = mobileNumberArg) {
            type = NavType.StringType
        }
    )
}
fun NavGraphBuilder.otpScreen(onBackPressed: () -> Unit, onForwardPressed: () -> Unit) {
    composable(route = OtpDest.routeWithArgs, arguments = OtpDest.arguments) {
        val number = it.arguments?.getString(OtpDest.mobileNumberArg)
        OtpRoute(
            phoneNumber = number!!,
            onSignInSuccess = onForwardPressed,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToOtp(phoneNumber: String) {
    this.navigate("${OtpDest.route}/$phoneNumber")
}

