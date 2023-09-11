package com.example.swvlclone.payment.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.payment.WalletRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object WalletDest : SwvlCloneDestination {
    override val route: String
        get() = "wallet_screen"
    override val name: String
        get() = "Wallet"
}

fun NavGraphBuilder.walletScreen(
    onBackPressed: () -> Unit,
    onAddCreditClick: () -> Unit,
) {
    composable(route = WalletDest.route) {
        WalletRoute(onBackPressed = onBackPressed, onAddCreditClick = onAddCreditClick)
    }
}