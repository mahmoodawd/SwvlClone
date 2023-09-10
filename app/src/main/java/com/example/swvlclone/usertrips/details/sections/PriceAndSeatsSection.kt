package com.example.swvlclone.usertrips.details.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun PriceAndSeatsSection(
    modifier: Modifier = Modifier,
    price: Int = 48,
    numberOfSeats: Int = 2,
) {
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {

        Column(modifier = Modifier) {
            Text(
                text = "$price EGP",
                style = MaterialTheme.typography.titleLarge,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Default.Money,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = "Cash",
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Base Fare",
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(
                text = "Number Of Seats",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = numberOfSeats.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "$price",
                modifier = Modifier.align(Alignment.End)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SectionPreview() {
    SwvlCloneTheme {
        PriceAndSeatsSection()
    }
}