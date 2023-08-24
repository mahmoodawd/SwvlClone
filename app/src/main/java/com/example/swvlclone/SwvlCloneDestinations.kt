package com.example.swvlclone

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface SwvlCloneDestinations {
    val route: String
}

object AuthDest : SwvlCloneDestinations {
    override val route: String
        get() = "auth_screen"
}

object MobileAuthDest : SwvlCloneDestinations {
    override val route: String
        get() = "mobile_auth_screen"
}

object OtpDest : SwvlCloneDestinations {
    override val route: String
        get() = "otp_screen"
    const val mobileNumberArg = "mobile_number"
    val routeWithArgs = "${route}/{${mobileNumberArg}}"
    val arguments = listOf(
        navArgument(name = mobileNumberArg) {
            type = NavType.StringType
        }
    )
}
