package com.example.swvlclone.usertrips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import java.util.Locale

@Composable
fun NoTripsLayout(
    modifier: Modifier = Modifier,
    onBookButtonPressed: () -> Unit = {},
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.no_upcoming_trips),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth()

                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = onBookButtonPressed
        ) {
            Text(text = stringResource(id = R.string.book_your_next_trip).uppercase(Locale.ROOT))

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoTripsPreview() {
    SwvlCloneTheme {
        NoTripsLayout()
    }
}