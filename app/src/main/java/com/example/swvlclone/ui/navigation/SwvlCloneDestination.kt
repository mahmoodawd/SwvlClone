package com.example.swvlclone.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.swvlclone.settings.navigation.SettingsDest

interface SwvlCloneDestination {
    val route: String
    val name: String

}

/*
object SplashDest : SwvlCloneDestination {
    override val route: String
        get() = "splash_screen"
    override val name: String
        get() = "Splash"
}
*/

object AuthDest : SwvlCloneDestination {
    override val route: String
        get() = "auth_screen"
    override val name: String
        get() = "Auth"
}

object MobileAuthDest : SwvlCloneDestination {
    override val route: String
        get() = "mobile_auth_screen"
    override val name: String
        get() = "Mobile Auth"
}

object OtpDest : SwvlCloneDestination {
    override val route: String
        get() = "otp_screen"
    override val name: String
        get() = "OTP"
    const val mobileNumberArg = "mobile_number"
    val routeWithArgs = "$route/{$mobileNumberArg}"
    val arguments = listOf(
        navArgument(name = mobileNumberArg) {
            type = NavType.StringType
        }
    )
}

object HomeDest : SwvlCloneDestination {
    override val route: String
        get() = "home_screen"
    override val name: String
        get() = "Home"
}

object LocationDest : SwvlCloneDestination {
    override val name: String
        get() = "Location"
    override val route: String
        get() = "location"
    const val tripTimeArg = "trip_time"
    val routeWithArgs = "$route/{$tripTimeArg}"
    val arguments = listOf(
        navArgument(name = tripTimeArg) {
            type = TimeArgType()
        },
    )
}

object TripsDest : SwvlCloneDestination {
    override val name: String
        get() = "Trips"
    override val route: String
        get() = "trips"
    const val tripTimeArg = "trip_time"
    const val tripLocationArg = "trip_location"
    val routeWithArgs = "${route}/{$tripTimeArg}/{$tripLocationArg}"
    val arguments = listOf(
        navArgument(name = tripTimeArg) {
            type = TimeArgType()
        },
        navArgument(name = tripLocationArg) {
            type = LocationArgType()
        }
    )
}

object YourTripsDest : SwvlCloneDestination {
    override val route: String
        get() = "your_trips"
    override val name: String
        get() = "Your Trips"
}

object WalletDest : SwvlCloneDestination {
    override val route: String
        get() = "wallet"
    override val name: String
        get() = "Wallet"
}

object PaymentDest : SwvlCloneDestination {
    override val route: String
        get() = "payment"
    override val name: String
        get() = "Payment"
}

object PrivateBusDest : SwvlCloneDestination {
    override val route: String
        get() = "private_bus"
    override val name: String
        get() = "Private Bus"
}

object HelpDest : SwvlCloneDestination {
    override val route: String
        get() = "help"
    override val name: String
        get() = "Help"
}

object PrivacyCenterDest : SwvlCloneDestination {
    override val route: String
        get() = "privacy_center"
    override val name: String
        get() = "Privacy Center"

}

val drawerItems = listOf(
    YourTripsDest,
    WalletDest,
    PaymentDest,
    PrivateBusDest,
    SettingsDest,
    HelpDest,
    PrivacyCenterDest
)