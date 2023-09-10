package com.example.swvlclone.usertrips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun UserTripItem(
    modifier: Modifier = Modifier,
    trip: TripModel,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "19/10/2023", color = MaterialTheme.colorScheme.outline)
                    Text(text = trip.startsAt)
                }
                Divider(
                    modifier = Modifier
                        .height(32.dp)
                        .width(3.dp)
                        .padding(vertical = 4.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = trip.startDest)
                    Text(text = trip.endDest)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "M834")
                Spacer(modifier = Modifier.width(16.dp))
                Icon(imageVector = Icons.Default.Money, contentDescription = null)
                Text(text = "Cash")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "48 EGP")

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TripItemPreview() {
    SwvlCloneTheme {
        UserTripItem(
            trip = TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            )
        ) {}
    }
}

