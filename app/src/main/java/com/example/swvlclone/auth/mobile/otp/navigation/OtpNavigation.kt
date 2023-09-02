package com.example.swvlclone.auth.mobile.otp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.auth.mobile.otp.OtpRoute
import com.example.swvlclone.ui.navigation.OtpDest

fun NavGraphBuilder.otpScreen(onBackPressed: () -> Unit, onForwardPressed: () -> Unit) {
    composable(route = OtpDest.routeWithArgs, arguments = OtpDest.arguments) {
        val number = it.arguments?.getString(OtpDest.mobileNumberArg)
        OtpRoute(
            phoneNumber = number!!,
            onForwardClick = onForwardPressed,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToOtp(phoneNumber: String) {
    this.navigate("${OtpDest.route}/$phoneNumber")
}

