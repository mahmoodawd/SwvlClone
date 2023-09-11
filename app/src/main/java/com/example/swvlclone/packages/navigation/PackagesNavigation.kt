package com.example.swvlclone.packages.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.packages.PackageRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object PackagesDest : SwvlCloneDestination {
    override val route: String
        get() = "packages_screen"
    override val name: String
        get() = "Packages"
}

fun NavGraphBuilder.packagesScreen(onBackPressed: () -> Unit) {
    composable(route = PackagesDest.route) {
        PackageRoute(
            onBackPressed = onBackPressed
        )
    }
}
