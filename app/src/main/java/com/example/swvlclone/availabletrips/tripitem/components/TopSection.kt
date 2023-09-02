package com.example.swvlclone.availabletrips.tripitem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.components.TextWithLeadingIcon

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    trip: TripModel,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 4.dp)
    ) {
        TripFromTo(
            from = trip.startsAt, to = trip.endsAt,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        TripDivider(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 4.dp)
        )
        TripInfo(
            timeToReachStart = trip.timeToReachStartInMins,
            timeToReachEnd = trip.timeToReachEndInMins,
            start = trip.startDest,
            end = trip.endDest
        )
    }
}

@Composable
private fun TripFromTo(
    modifier: Modifier = Modifier,
    from: String,
    to: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        StyledText(text = from)
        StyledText(text = to)
    }
}

@Composable
private fun TripDivider(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .width(5.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Brightness1, contentDescription = null,
            tint = Color.Blue
        )
        Divider(
            modifier = Modifier
                .height(12.dp)
                .width(3.dp)
        )
        Divider(modifier = Modifier.height(32.dp))
        Divider(
            modifier = Modifier
                .height(12.dp)
                .width(3.dp)
        )
        Icon(
            imageVector = Icons.Default.Brightness1, contentDescription = null,
            tint = Color.Red
        )
    }

}

@Composable
private fun TripInfo(
    modifier: Modifier = Modifier,
    timeToReachStart: String,
    timeToReachEnd: String,
    start: String,
    end: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        TextWithLeadingIcon(
            text = timeToReachStart,
            icon = Icons.Default.DirectionsCar,
            color = MaterialTheme.colorScheme.outline
        )
        StyledText(text = start)
        StyledText(text = end)
        TextWithLeadingIcon(
            text = timeToReachEnd,
            icon = Icons.Default.DirectionsCar,
            color = MaterialTheme.colorScheme.outline
        )

    }
}

@Composable
private fun StyledText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall
    )
}