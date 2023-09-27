package com.example.swvlclone.home.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.home.SaveLocationRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination


object SaveLocationDest : SwvlCloneDestination {
    override val route: String
        get() = "save_location"
    const val locationTypeArg = "location_type"
    val routeWithArgs = "${route}/{${locationTypeArg}}"
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(name = locationTypeArg) {
                type = NavType.StringType
            }
        )
}

fun NavGraphBuilder.saveLocationScreen(
    onBackPressed: () -> Unit,
) {
    composable(route = SaveLocationDest.routeWithArgs) {
        val locationType = it.arguments?.getString(SaveLocationDest.locationTypeArg)!!
        SaveLocationRoute(locationType = locationType, onBackPressed = onBackPressed)
    }
}

fun NavController.navigateToSaveLocation(locationType: String) {
    navigate("${SaveLocationDest.route}/${locationType}")
}