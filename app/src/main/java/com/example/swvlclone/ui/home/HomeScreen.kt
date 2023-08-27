package com.example.swvlclone.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.components.SwvlCloneNavigationDrawer
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.home.sections.BottomSection
import com.example.swvlclone.ui.home.sections.FavoriteLocationSection
import com.example.swvlclone.ui.home.sections.GoSection
import com.example.swvlclone.ui.home.sections.OfferItem
import com.example.swvlclone.ui.home.sections.TripTimeBottomSheet
import com.example.swvlclone.ui.home.sections.TripsSection
import com.example.swvlclone.ui.navigation.HomeDest
import com.example.swvlclone.ui.navigation.drawerItems
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onLocationClick: () -> Unit = {},
    onDrawerItemClick: (String) -> Unit = {},
    currentDestinationRoute: String? = HomeDest.route
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
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
                    onDrawerItemClick(route)
                },
                currentDestination = currentDestinationRoute!!
            )

        }
    ) { paddingValues ->
        val bottomSheetState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,

            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetElevation = 4.dp,
            sheetContent = {
                TripTimeBottomSheet()
            }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OfferItem()
                TripsSection()
                GoSection(
                    onLocationCardClick = { onLocationClick() },
                    onTripTimeClick = {
                        coroutineScope.launch { bottomSheetState.show() }
                    }
                )
                FavoriteLocationSection()
                BottomSection()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    SwvlCloneTheme {
        HomeScreen()
    }
}