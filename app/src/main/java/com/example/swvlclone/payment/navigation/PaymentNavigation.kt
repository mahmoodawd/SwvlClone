package com.example.swvlclone.payment.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.payment.PaymentRoute
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object PaymentDest : SwvlCloneDestination {
    override val route: String
        get() = "payment_screen"
    override val name: String
        get() = "Payment"
}

fun NavGraphBuilder.paymentScreen(
    onBackPressed: () -> Unit,
    onAddCreditClick: () -> Unit,
) {
    composable(route = PaymentDest.route) {
        PaymentRoute(onBackPressed = onBackPressed, onAddCreditClick = onAddCreditClick)
    }
}