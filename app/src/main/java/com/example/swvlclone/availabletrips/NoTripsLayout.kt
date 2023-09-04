package com.example.swvlclone.availabletrips

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import java.util.Locale

@Composable
fun NoTripsLayout(
    modifier: Modifier = Modifier,
    onRequestButtonPressed: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(id = R.drawable.mini_bus),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(150.dp)
                .shadow(shape = CircleShape, elevation = 8.dp, clip = true)
                .background(Color.White)
                .clip(CircleShape)
                .padding(4.dp)
        )
        Text(
            text = stringResource(id = R.string.no_rides),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.no_rides_notice),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = { }) {
            Text(text = stringResource(id = R.string.request_ride).uppercase(Locale.ROOT))

        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Composable
private fun TopHeader(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(12.dp)
    ) {
        BackButton {}
        Text(
            text = stringResource(id = R.string.choose_your_trip),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoTripsPreview() {
    SwvlCloneTheme {
        NoTripsLayout()
    }
}