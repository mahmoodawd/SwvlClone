package com.example.swvlclone.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.settings.SocialAccountsRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object SocialAccountsDest : SwvlCloneDestination {
    override val route: String
        get() = "social_accounts_screen"
    override val name: String
        get() = "Social Accounts"
    const val socialAccountsArg = "social_accounts"
    val routeWithArgs = "${route}/{$socialAccountsArg}"
    override val arguments = listOf(
        navArgument(name = socialAccountsArg) {
            type = NavType.StringArrayType
        }
    )
}

fun NavGraphBuilder.socialAccountsScreen(
    onBackPressed: () -> Unit
) {
    composable(route = SocialAccountsDest.routeWithArgs) {
        val connectedAccounts =
            it.arguments?.getStringArray(SocialAccountsDest.socialAccountsArg) ?: ""
        SocialAccountsRoute(
            onBackPressed = onBackPressed,
            connectedAccounts = connectedAccounts.toString().split(",")
        )
    }
}

fun NavController.navigateToSocialAccounts(accounts: ArrayList<String>) {
    navigate("${SocialAccountsDest.route}/$accounts")
}