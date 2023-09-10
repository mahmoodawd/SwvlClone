package com.example.swvlclone.usertrips.details.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun TripInfoSection(
    modifier: Modifier = Modifier,
    trip: TripModel,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TripTime(
            from = trip.startsAt, to = trip.endsAt,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        TripDivider(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 4.dp)
        )
        TripLocation(
            start = trip.startDest,
            end = trip.endDest
        )
    }
}

@Composable
private fun TripTime(
    modifier: Modifier = Modifier,
    from: String,
    to: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)

    ) {
        Text(
            text = "Tomorrow",
            color = MaterialTheme.colorScheme.outline
        )
        Text(text = from)
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = to)
    }
}

@Composable
private fun TripDivider(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Icon(
            imageVector = Icons.Default.Brightness1, contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(18.dp)
        )
        Divider(
            modifier = Modifier
                .background(brush = Brush.verticalGradient(colors = listOf(Color.Red, Color.Blue)))
                .width(8.dp)
                .height(45.dp)
        )
        Icon(
            imageVector = Icons.Default.Brightness1, contentDescription = null,
            tint = Color.Blue,
            modifier = Modifier.size(18.dp)
        )
    }

}

@Composable
private fun TripLocation(
    modifier: Modifier = Modifier,
    start: String,
    end: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = start, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = end, fontWeight = FontWeight.Bold)
    }
}


@Preview(showBackground = true)
@Composable
fun TripInfoPreview() {
    SwvlCloneTheme {
        TripInfoSection(
            trip = TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            ),
        )
    }
}