package com.example.swvlclone.home.sections

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme


@Composable
fun TripsSection(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(tripsTypesItems) { tripItem ->
            TripTypeItem(
                title = tripItem.title,
                subtitle = tripItem.subtitle,
                icon = tripItem.icon,
                onClick = onItemClick
            )
        }

    }
}

@Composable
fun TripTypeItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes subtitle: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = subtitle),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(48.dp)
                .shadow(shape = CircleShape, elevation = 8.dp, clip = true)
                .background(Color.White)
                .clip(CircleShape)
                .padding(4.dp)
                .clickable { onClick() }
        )
        Text(
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(id = subtitle),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun TripTypesPreview() {
    SwvlCloneTheme {
        TripsSection() {}
    }
}


private val tripsTypesItems = listOf(
    R.drawable.mini_bus to R.string.daily to R.string.daily_desc,
    R.drawable.travel_bag to R.string.travel to R.string.travel_desc,
    R.drawable.bus_logo to R.string.private_ride to R.string.private_ride_desc,
).map {
    DrawableStringStringTriple(
        icon = it.first.first, //Refers to the drawable
        title = it.first.second, // Refers to  the title
        subtitle = it.second // Refers to subtitle
    )
}

private data class DrawableStringStringTriple(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val subtitle: Int
)
