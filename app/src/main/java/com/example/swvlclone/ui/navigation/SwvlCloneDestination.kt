package com.example.swvlclone.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.swvlclone.packages.navigation.PackagesDest
import com.example.swvlclone.payment.navigation.PaymentDest
import com.example.swvlclone.payment.navigation.WalletDest
import com.example.swvlclone.privacycenter.HelpDest
import com.example.swvlclone.privacycenter.navigation.PrivacyCenterDest
import com.example.swvlclone.settings.navigation.SettingsDest
import com.example.swvlclone.usertrips.navigation.UserTripsDest

interface SwvlCloneDestination {
    val route: String
    val name: String
        get() = ""
    val arguments: List<NamedNavArgument>
        get() = emptyList()

}

val drawerItems = listOf(
    UserTripsDest,
    WalletDest,
    PaymentDest,
    PackagesDest,
    SettingsDest,
    HelpDest,
    PrivacyCenterDest
)