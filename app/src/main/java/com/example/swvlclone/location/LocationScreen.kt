package com.example.swvlclone.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.location.sections.FavoriteLocationsSection
import com.example.swvlclone.location.sections.LocationPickerSection
import com.example.swvlclone.location.sections.MapSection
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun LocationRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onLocationPicked: (TripLocation) -> Unit = {}
) {
    LocationScreen(
        onBackPressed = onBackPressed,
        onLocationPicked = onLocationPicked,
        modifier = modifier
    )
}

@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onLocationPicked: (TripLocation) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BackButton { onBackPressed() }
        LocationPickerSection(onLocationPicked = { onLocationPicked(it) })
        ThickDivider()
        FavoriteLocationsSection()
        ThickDivider()
        MapSection()
        Divider()
    }
}

@Composable
private fun ThickDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(MaterialTheme.colorScheme.outline)
    )
}


@Preview(showBackground = true)
@Composable
private fun LocationScreenPreview() {
    SwvlCloneTheme {
        LocationScreen(
            onBackPressed = {},
            onLocationPicked = {})
    }

}