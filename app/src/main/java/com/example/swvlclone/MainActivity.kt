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
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val context = LocalContext.current
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                SwvlCloneTopBar(
                    onMenuIconClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                SwvlCloneNavigationDrawer(
                    items = drawerItems,
                    onItemClick = { route ->
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
//                        navController.navigate(it)
                        Toast.makeText(
                            context,
                            drawerItems.find { it.route == route }!!.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    currentDestination = currentDestination?.route
                        ?: YourTripsDest.route
                )
            }
        ) { paddingValue ->
            SwvlCloneNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValue)
            )
        }
    }
}

