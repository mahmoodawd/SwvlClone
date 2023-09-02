package com.example.swvlclone.availabletrips.tripitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.availabletrips.tripitem.components.BottomSection
import com.example.swvlclone.availabletrips.tripitem.components.TopSection
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.components.TextWithLeadingIcon
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun TripItem(
    modifier: Modifier = Modifier,
    trip: TripModel,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {

        Column {
            TopSection(
                trip = trip,
                modifier = Modifier.clickable { onClick() }
            )
            TextWithLeadingIcon(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp),
                text = "Limited Time Deal",
                icon = Icons.Filled.AccessTimeFilled,
                color = Color(0xFFf39f55),

                )
            BottomSection(
                modifier = Modifier
                    .padding(8.dp), busType = "Premium", oldPrice = 68, newPrice = 120
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TripItemPreview() {
    SwvlCloneTheme {
        TripItem(
            trip = TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            )
        )
    }
}

