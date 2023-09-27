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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.swvlclone.R
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.comon.utils.extractFullDateTime
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.home.sections.FavoriteLocationSection
import com.example.swvlclone.home.sections.FromCairoSection
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
import timber.log.Timber
import java.util.Calendar

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onLocationClick: (TripTime) -> Unit,
    onDrawerItemClick: (String) -> Unit,
    onFavoriteLocationClick: (Boolean, String, TripLocation, TripTime) -> Unit,
    onOfferClick: () -> Unit,
    onTripCategoryClick: () -> Unit,
    onCityClick: () -> Unit,
    currentDestinationRoute: String,
    viewModel: HomeViewModel = hiltViewModel(),
    googleAuthUiClient: AuthUiClient
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val currentUser = googleAuthUiClient.getSignedInUser()

    val savedLocationsState by viewModel.userLocationsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        Timber.d("Launched Effect for getting user locations")
        viewModel.getUserLocations()
    }

    HomeScreen(
        onLocationClick = onLocationClick,
        onDrawerItemClick = onDrawerItemClick,
        onFavoriteLocationClick = onFavoriteLocationClick,
        onOfferClick = onOfferClick,
        onTripCategoryClick = onTripCategoryClick,
        onCityClick = onCityClick,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        currentDestinationRoute = currentDestinationRoute,
        savedLocationsState = savedLocationsState,
        userName = currentUser?.userName!!,
        userPhotoUrl = currentUser.profilePhotoUrl,
        modifier = modifier,
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLocationClick: (TripTime) -> Unit,
    onDrawerItemClick: (String) -> Unit,
    onFavoriteLocationClick: (Boolean, String, TripLocation, TripTime) -> Unit,
    onOfferClick: () -> Unit,
    onTripCategoryClick: () -> Unit,
    onCityClick: () -> Unit,
    scaffoldState: ScaffoldState,
    savedLocationsState: UserLocationUiState,
    coroutineScope: CoroutineScope,
    currentDestinationRoute: String,
    userName: String,
    userPhotoUrl: String
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            SwvlCloneTopBar(
                title = stringResource(R.string.hey).plus(userName),
                subtitle = stringResource(R.string.where_are_you_going),
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
                userName = userName,
                userPhotoUrl = userPhotoUrl,
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
        val today = Calendar.getInstance()
        var selectedTripTime by remember {
            mutableStateOf(
                TripTime(
                    day = today.timeInMillis,
                    hour = today.get(Calendar.HOUR_OF_DAY)
                )
            )
        }
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
                OfferItem(onClick = onOfferClick)
                TripsSection(onItemClick = onTripCategoryClick)
                GoSection(
                    selectedTripTime = extractFullDateTime(selectedTripTime),
                    onLocationCardClick = {
                        onLocationClick(selectedTripTime)
                    },
                    onTripTimeClick = {
                        coroutineScope.launch { bottomSheetState.show() }
                    }
                )
                FavoriteLocationSection(
                    userLocationsState = savedLocationsState,
                    onFavoriteLocationClick = { isLocationSet, locationType, tripLocation ->
                        onFavoriteLocationClick(
                            isLocationSet,
                            locationType,
                            tripLocation,
                            selectedTripTime
                        )
                    }
                )
                FromCairoSection(onCityClick = onCityClick)
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
            onFavoriteLocationClick = { _, _, _, _ -> },
            onOfferClick = {},
            onTripCategoryClick = {},
            onCityClick = {},
            scaffoldState = rememberScaffoldState(),
            coroutineScope = rememberCoroutineScope(),
            currentDestinationRoute = "",
            savedLocationsState = UserLocationUiState.Empty,
            userName = "",
            userPhotoUrl = ""
        )
    }
}