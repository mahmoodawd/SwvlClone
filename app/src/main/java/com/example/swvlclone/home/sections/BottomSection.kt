package com.example.swvlclone.home.sections

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun BottomSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(R.string.travel_from_cairo),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        LazyRow {
            items(destinations) { destination ->
                DestinationItem(
                    location = destination.location,
                    price = destination.price,
                    image = destination.image
                )
            }
        }
    }

}


@Composable
private fun DestinationItem(
    modifier: Modifier = Modifier,
    @StringRes location: Int,
    price: Int,
    @DrawableRes image: Int
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = location),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .size(150.dp)
        )
        Text(
            text = stringResource(id = location),
            textAlign = TextAlign.Start,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
        )
        Text(
            text = "from $price EGP",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelSmall,
            softWrap = false,
            maxLines = 1,
            minLines = 1,
            color = MaterialTheme.colorScheme.outline,
        )
    }
}

@Preview
@Composable
private fun BottomSectionPreview() {
    SwvlCloneTheme {
        BottomSection()
    }
}

@Preview
@Composable
private fun DestinationItemPreview() {
    SwvlCloneTheme {
        DestinationItem(
            location = R.string.travel_from_cairo,
            price = 130,
            image = R.drawable.hurghada,
        )
    }
}

private val destinations = listOf(
    R.string.alex to 130 to R.drawable.hurghada,
    R.string.hurghada to 265 to R.drawable.hurghada,
    R.string.dahab to 340 to R.drawable.traffic,
    R.string.mansura to 95 to R.drawable.hurghada,
    R.string.ismailia to 85 to R.drawable.hurghada,
    R.string.tanta to 75 to R.drawable.traffic,
).map {
    DestinationModel(location = it.first.first, price = it.first.second, image = it.second)
}

data class DestinationModel(
    @StringRes val location: Int,
    val price: Int,
    @DrawableRes val image: Int
)