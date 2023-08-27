package com.example.swvlclone.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface SwvlCloneDestination {
    val route: String
    val name: String

}

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
object TripsDest : SwvlCloneDestination {
    override val route: String
        get() = "trips"
    override val name: String
        get() = "Trips"
}

object LocationDest : SwvlCloneDestination {
    override val route: String
        get() = "location"
    override val name: String
        get() = "Location"
}
object HomeDest : SwvlCloneDestination {
    override val route: String
        get() = "home_screen"
    override val name: String
        get() = "Home"
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

object SettingsDest : SwvlCloneDestination {
    override val route: String
        get() = "settings"
    override val name: String
        get() = "Settings"
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