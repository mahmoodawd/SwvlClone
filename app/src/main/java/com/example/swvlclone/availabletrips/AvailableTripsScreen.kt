package com.example.swvlclone.availabletrips

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.availabletrips.sections.DaysSection
import com.example.swvlclone.availabletrips.sections.HoursSection
import com.example.swvlclone.availabletrips.tripitem.TripItem
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun AvailableTripsRoute(
    onBackPressed: () -> Unit,
    onTripItemClick: (TripModel) -> Unit,
    selectedTripTime: TripTime,
    selectedTripLocation: TripLocation,
) {
    val availableTrips = createTripList()
    /*TODO availableTrips should be retrieved from viewModel
            By passing trip time and location
     */
    AvailableTripsScreen(
        onBackPressed = onBackPressed,
        onTripItemClick = onTripItemClick,
        availableTrips
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AvailableTripsScreen(
    onBackPressed: () -> Unit,
    onTripItemClick: (TripModel) -> Unit,
    availableTrips: List<TripModel> = emptyList()
) {
    Scaffold(
        topBar = {
            SwvlCloneTopBar(
                title = stringResource(id = R.string.choose_your_trip),
                icon = R.drawable.arrow_back,
                onIconClick = { onBackPressed() }
            )
        }
    ) { paddingValues ->


        if (availableTrips.isEmpty()) {
            NoTripsLayout(modifier = Modifier.padding(paddingValues))
        } else {
            //Should be retrieved from user selection at home
            var selectedDayIndex = 0
            var selectedHourIndex = 0


            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                stickyHeader {
                    Column {
                        DaysSection(
                            modifier = Modifier.background(Color.White),
                            days = createDaysList(),
                            userSelection = "Wed, 23 Aug",
                            onDaySelected = { selectedDayIndex = it }
                        )
                        HoursSection(
                            hours = createTimeList(),
                            onHourSelected = { selectedHourIndex = it },
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                items(availableTrips) { tripModel ->
                    TripItem(trip = tripModel, onClick = { onTripItemClick(tripModel) })
                }

            }
        }

    }
}


@Preview
@Composable
private fun TripsScreenPreview() {
    SwvlCloneTheme {
        AvailableTripsScreen(
            onBackPressed = {},
            onTripItemClick = {},
            createTripList()
        )
    }
}

private fun createTripList(): List<TripModel> {
    return listOf(
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
        ),
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
        ),

        )
}

private fun createDaysList(): List<String> {
    return listOf(
        "Tomorrow",
        "Wed, 23 Aug",
        "Thur, 24 Aug",
        "Fir, 25 Aug",
        "Sat, 26 Aug"
    )
}

private fun createTimeList(): List<String> {
    val timeList = mutableListOf<String>()
    for (hour in 0 until 24) {
        val startHour = hour.toString().padStart(2, '0')
        val endHour = (hour + 1).toString().padStart(2, '0')
        val isPM = hour >= 12
        timeList.add("$startHour ${if (isPM) "pm" else "am"} - $endHour ${if (isPM) "pm" else "am"}")
    }
    return timeList
}