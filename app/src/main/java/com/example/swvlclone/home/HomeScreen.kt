package com.example.swvlclone.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.home.sections.BottomSection
import com.example.swvlclone.home.sections.FavoriteLocationSection
import com.example.swvlclone.home.sections.GoSection
import com.example.swvlclone.home.sections.OfferItem
import com.example.swvlclone.home.sections.TripTimeBottomSheet
import com.example.swvlclone.home.sections.TripsSection
import com.example.swvlclone.ui.components.SwvlCloneNavigationDrawer
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.navigation.drawerItems
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    onLocationClick: (TripTime) -> Unit,
    onDrawerItemClick: (String) -> Unit,
    currentDestinationRoute: String,
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    HomeScreen(
        onLocationClick = onLocationClick,
        onDrawerItemClick = onDrawerItemClick,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        currentDestinationRoute = currentDestinationRoute,
        modifier = modifier,
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onLocationClick: (TripTime) -> Unit,
    onDrawerItemClick: (String) -> Unit,
    currentDestinationRoute: String,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SwvlCloneTopBar(
                title = "Hey, User",
                subtitle = "Where are you going?",
                icon = R.drawable.burger_menu_left,
                onIconClick = {
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
        var selectedTripTime = TripTime(day = "", hour = "")
        val bottomSheetState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,

            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetElevation = 4.dp,
            sheetContent = {
                TripTimeBottomSheet(
                    onProceedButtonClick = { tripTime ->
                        coroutineScope.launch { bottomSheetState.hide() }
                        selectedTripTime = tripTime

                    }
                )
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
                    onLocationCardClick = {
                        onLocationClick(selectedTripTime)
                    },
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
        HomeScreen(
            onLocationClick = {},
            onDrawerItemClick = {},
            scaffoldState = rememberScaffoldState(),
            coroutineScope = rememberCoroutineScope(),
            currentDestinationRoute = ""
        )
    }
}