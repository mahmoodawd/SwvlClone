package com.example.swvlclone.availabletrips.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import java.util.Locale

@Composable
fun SuccessfulBookingSheetContent(
    modifier: Modifier = Modifier,
    onBookReturnClick: () -> Unit,
    onViewTripDetailsClick: () -> Unit,
    onClose: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val offerAnnotatedString = buildAnnotatedString {
            val textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            ).toSpanStyle()

            val linkStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            ).toSpanStyle()
            withStyle(textStyle) {
                append(stringResource(R.string.plan_your_rides))
            }
            append("\n")
            withStyle(linkStyle) {
                append(stringResource(R.string.learn_more))
            }
        }
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Start)
                .requiredSize(36.dp)
                .clickable { onClose() }
        )
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = null,
            tint = Color(0xFF00a15d),
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(shape = CircleShape, color = Color(0xFFb3e3ce))
                .border(width = 4.dp, color = Color(0xFF00a15d), shape = CircleShape)
                .padding(4.dp)
        )
        Text(
            text = stringResource(R.string.trip_successfully_booked),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp) // Add padding to exclude the top side
                .shadow(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 16.dp,
                    clip = true,
                    ambientColor = MaterialTheme.colorScheme.outline
                )
                .background(Color.White)
                .clip(RoundedCornerShape(16.dp))
                .padding(4.dp)
        ) {
            Text(text = offerAnnotatedString, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.EditCalendar,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(56.dp)
            )
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onBookReturnClick
        ) {
            Text(
                text = stringResource(R.string.book_your_return_trip).uppercase(Locale.ROOT),
                letterSpacing = 2.sp,
            )
        }
        OutlinedButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onViewTripDetailsClick
        ) {
            Text(
                text = stringResource(R.string.view_your_trip_details).uppercase(Locale.ROOT),
                color = MaterialTheme.colorScheme.outline,
                letterSpacing = 2.sp,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SheetPreview() {
    SwvlCloneTheme {
        SuccessfulBookingSheetContent(
            onBookReturnClick = {},
            onViewTripDetailsClick = {},
            onClose = {})
    }
}
