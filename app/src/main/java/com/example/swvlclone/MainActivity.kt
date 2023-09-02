package com.example.swvlclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.swvlclone.ui.navigation.SwvlCloneNavHost
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContent {
            SwvlCloneApp()
        }
    }
}


@Composable
fun SwvlCloneApp() {
    SwvlCloneTheme {
        val navController = rememberNavController()

        SwvlCloneNavHost(
            navController = navController,
        )

    }
}

