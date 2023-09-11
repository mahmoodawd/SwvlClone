package com.example.swvlclone.payment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.payment.AddCardRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object AddCardDest : SwvlCloneDestination {
    override val route: String
        get() = "add_card_screen"
}

fun NavGraphBuilder.addCardScreen(
    onBackPressed: () -> Unit,
) {
    composable(route = AddCardDest.route) {
        AddCardRoute(onBackPressed = onBackPressed)
    }
}

fun NavController.navigateToAddCard() {
    navigate(AddCardDest.route)
}