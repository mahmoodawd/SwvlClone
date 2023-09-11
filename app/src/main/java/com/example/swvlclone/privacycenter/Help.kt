package com.example.swvlclone.privacycenter

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.swvlclone.ui.navigation.SwvlCloneDestination

object HelpDest : SwvlCloneDestination {
    override val route: String
        get() = "help"
    override val name: String
        get() = "Help"
}
fun NavGraphBuilder.help() {
    composable(route = HelpDest.route) {
        OpenLink("https://www.google.com/")
    }
}

@Composable
fun OpenLink(url: String) {
    val context = LocalContext.current
    // Open the link in the browser
    LaunchedEffect(url) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}