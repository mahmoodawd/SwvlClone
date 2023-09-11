package com.example.swvlclone.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.settings.CityRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object CityDest : SwvlCloneDestination {
    override val route: String
        get() = "city_screen"
    override val name: String
        get() = "City"
    const val cityArg = "city"
    val routeWithArgs = "${route}/{$cityArg}"
    override val arguments = listOf(
        navArgument(name = cityArg) {
            type = NavType.StringType
        }
    )
}

fun NavGraphBuilder.cityScreen(
    onBackPressed: () -> Unit
) {
    composable(route = CityDest.routeWithArgs) {
        val city = it.arguments?.getString(CityDest.cityArg) ?: ""
        CityRoute(onBackPressed = onBackPressed, currentCity = city)
    }
}

fun NavController.navigateToCityChange(city: String) {
    navigate("${CityDest.route}/$city")
}