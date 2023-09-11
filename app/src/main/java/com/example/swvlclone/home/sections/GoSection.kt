package com.example.swvlclone.home.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun GoSection(
    modifier: Modifier = Modifier,
    onTripTimeClick: () -> Unit = {},
    onLocationCardClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Let's Go!",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        TripTimeButton {
            onTripTimeClick()
        }
        LocationCard {
            onLocationCardClick()
        }
    }
}

@Composable
private fun TripTimeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val color = MaterialTheme.colorScheme.outline
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface
            )
            .clickable {
                onClick()
            }
    ) {
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            tint = color,
            modifier = Modifier.padding(start = 4.dp)

        )
        Text(
            text = "Now",
            color = color
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = color
        )
    }
}

@Composable
private fun LocationCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface
            ),
    ) {

        LocationField(
            tint = Color(0xFF6960e7),
            icon = Icons.Default.LocationOn,
            text = R.string.my_current_location
        )
        Divider(modifier = Modifier.padding(start = 20.dp))
        LocationField(
            tint = Color.Red,
            textColor = MaterialTheme.colorScheme.outline,
            icon = Icons.Default.Brightness1,
            text = R.string.where_to
        )
    }
}

@Composable
private fun LocationField(
    tint: Color,
    textColor: Color = tint,
    icon: ImageVector,
    @StringRes text: Int

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = text),
            tint = tint,
            modifier = Modifier
                .size(32.dp)
                .padding(4.dp)
        )
        Text(
            text = stringResource(id = text),
            color = textColor
        )
    }
}


@Composable
fun TripTimeBottomSheet(
    modifier: Modifier = Modifier,
    onProceedButtonClick: (TripTime) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Show Trips Starting From",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        Divider()
        DateTimePickerItem(text = R.string.time) {
            //TODO Show Time Picker
        }
        Divider()
        DateTimePickerItem(text = R.string.date) {
            //TODO Show date Picker
        }
        Divider()
        Button(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = {
                //TODO Get picked day and hour
                onProceedButtonClick(TripTime(day = "Tomorrow", hour = "06:00 am"))
            }) {
            Text(text = "Proceed".uppercase())

        }
    }
}

@Composable
private fun DateTimePickerItem(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    pickedValue: String = "Now",
    onItemClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Text(
            text = stringResource(id = text),
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = pickedValue,
            modifier = Modifier.clickable {
                onItemClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GoSectionPreview() {
    SwvlCloneTheme {
        GoSection()
    }
}

@Preview(showBackground = true)
@Composable
fun TripTimeSheetPreview() {
    TripTimeBottomSheet()

}