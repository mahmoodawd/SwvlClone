package com.example.swvlclone.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.swvlclone.settings.LanguagesRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object LanguageDest : SwvlCloneDestination {
    override val route: String
        get() = "language_screen"
    override val name: String
        get() = "language"
    const val languageArg = "language"
    val routeWithArgs = "${LanguageDest.route}/{$languageArg}"
    val arguments = listOf(
        navArgument(name = languageArg) {
            type = NavType.StringType
        }
    )
}

fun NavGraphBuilder.languageScreen(
    onBackPressed: () -> Unit
) {
    composable(route = LanguageDest.routeWithArgs) {
        val currentLanguage = it.arguments?.getString(LanguageDest.languageArg) ?: ""
        LanguagesRoute(currentLanguage = currentLanguage, onBackPressed = onBackPressed)
    }
}

fun NavController.navigateToLanguageChange(language: String) {
    navigate("${LanguageDest.route}/$language")
}