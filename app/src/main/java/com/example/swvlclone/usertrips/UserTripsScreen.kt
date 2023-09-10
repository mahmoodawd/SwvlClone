package com.example.swvlclone.usertrips

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme


@Composable
fun UserTripsRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onItemClick: (TripModel) -> Unit
) {
    val trips = listOf(
        TripModel(
            startsAt = "09:00 AM",
            endsAt = "12:00 PM",
            timeToReachStartInMins = "30",
            timeToReachEndInMins = "60",
            startDest = "New York City",
            endDest = "Los Angeles",
        ),
        TripModel(
            startsAt = "09:00 AM",
            endsAt = "12:00 PM",
            timeToReachStartInMins = "30",
            timeToReachEndInMins = "60",
            startDest = "New York City",
            endDest = "Los Angeles",
        )
    )
    UserTripsScreen(
        onBackPressed = onBackPressed,
        userTrips = trips,
        onItemClick = onItemClick,
        modifier = modifier
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserTripsScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    userTrips: List<TripModel>,
    onItemClick: (TripModel) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }
        val tabs = listOf("Upcoming", "Past")
        BackButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp),
            onClick = onBackPressed
        )
        LazyColumn(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            stickyHeader {
                TabRow(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(32.dp),
                    selectedTabIndex = selectedTabIndex,
                    indicator = { Box(modifier = Modifier.background(Color.Transparent)) {} },
                    divider = {
                        Box(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .width(8.dp)
                        ) {}
                    }
                ) {
                    tabs.forEachIndexed { tabIndex, tabTitle ->
                        val isSelected = selectedTabIndex == tabIndex
                        Tab(
                            text = { Text(text = tabTitle) },
                            modifier = modifier
                                .padding(6.dp)
                                .shadow(
                                    shape = RoundedCornerShape(32.dp),
                                    elevation = 2.dp,
                                    clip = true
                                )
                                .background(
                                    shape = RoundedCornerShape(32.dp),
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White
                                ),
                            selected = isSelected,
                            selectedContentColor = Color.White,
                            unselectedContentColor = MaterialTheme.colorScheme.outline,
                            onClick = {
                                selectedTabIndex = tabIndex
                                //TODO Change displayed tripList according to selected tab
                            },
                        )
                    }

                }
            }
            items(userTrips) {
                UserTripItem(
                    trip = it,
                    onClick = {
                        onItemClick(it)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserTripsPreview() {
    SwvlCloneTheme {
        UserTripsScreen(onBackPressed = { }, userTrips = listOf(
            TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            ),
            TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            )
        ), onItemClick = {})
    }
}