package com.example.swvlclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swvlclone.ui.auth.AuthScreen
import com.example.swvlclone.ui.auth.MobileAuthScreen
import com.example.swvlclone.ui.auth.OTPScreen
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContent {
            SwvlCloneTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SwvlCloneNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SwvlCloneNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = OtpDest.routeWithArgs,
        modifier = modifier
    ) {

        composable(route = AuthDest.route) {
            AuthScreen(onPhoneFieldClick = {
                navController.navigate(MobileAuthDest.route)
            })
        }

        composable(route = MobileAuthDest.route) {
            MobileAuthScreen(onForwardButtonClick = { phoneNumber ->
                navController.navigateToOtpScreen(phoneNumber)
                Timber.i("ForwardButton Pressed")
            },
                onBackPressed = {
                    navController.popBackStack()
                })
        }

        composable(route = OtpDest.routeWithArgs) {
            val number = it.arguments?.getString(OtpDest.mobileNumberArg)
            OTPScreen(
                phoneNumber = number ?: "01141680631",
                onBackPressed = { navController.popBackStack() })
        }
    }

}

private fun NavHostController.navigateToOtpScreen(phoneNumber: String) {
    this.navigate("${OtpDest.route}/$phoneNumber")
}