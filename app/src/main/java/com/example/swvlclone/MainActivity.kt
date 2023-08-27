package com.example.swvlclone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swvlclone.ui.components.SwvlCloneNavigationDrawer
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.navigation.HomeDest
import com.example.swvlclone.ui.navigation.SwvlCloneNavHost
import com.example.swvlclone.ui.navigation.YourTripsDest
import com.example.swvlclone.ui.navigation.drawerItems
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.launch
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

