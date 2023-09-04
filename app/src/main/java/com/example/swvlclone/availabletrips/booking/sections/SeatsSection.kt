package com.example.swvlclone.availabletrips.booking.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R

@Composable
fun SeatsSection(
    modifier: Modifier = Modifier,
) {
    var numberOfSeats by remember {
        mutableIntStateOf(1)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 16.dp)
            .background(MaterialTheme.colorScheme.surface)

    ) {
        val seatsAnnotatedString = buildAnnotatedString {
            val numberStyle = TextStyle(
                fontWeight = FontWeight.Bold
            ).toSpanStyle()
            val textStyle = TextStyle(
                color = MaterialTheme.colorScheme.outline
            ).toSpanStyle()

            withStyle(numberStyle) {
                append(numberOfSeats.toString())
            }
            append(" ")
            withStyle(textStyle) {
                append(pluralStringResource(id = R.plurals.seats, count = numberOfSeats))
            }
        }

        ModifySeatsNumberButton(
            icon = Icons.Default.Remove,
            onclick = { numberOfSeats-- },
            enabled = numberOfSeats > 1
        )
        Text(
            text = seatsAnnotatedString,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ModifySeatsNumberButton(
            icon = Icons.Default.Add,
            onclick = { numberOfSeats++ },
            enabled = numberOfSeats < 9
        )

    }

}
@Composable
private fun ModifySeatsNumberButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onclick: () -> Unit,
    enabled: Boolean,
) {
    val color = if (enabled) Color.Black else MaterialTheme.colorScheme.outline
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color,
        modifier = modifier
            .requiredSize(48.dp)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = color,
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
            .clickable(enabled) { onclick() }
    )
}

