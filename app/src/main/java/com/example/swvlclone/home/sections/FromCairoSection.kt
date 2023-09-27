package com.example.swvlclone.home.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import coil.compose.SubcomposeAsyncImage
import com.example.swvlclone.R
import com.example.swvlclone.comon.composeext.shimmerEffect
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun FromCairoSection(
    modifier: Modifier = Modifier,
    onCityClick: () -> Unit,
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
                DestinationCityItem(
                    location = destination.location,
                    price = destination.price,
                    imageUrl = destination.imageUrl,
                    onClick = onCityClick,
                )
            }
        }
    }

}


@Composable
private fun DestinationCityItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes location: Int,
    price: Int,
    imageUrl: String
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = stringResource(id = location),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .size(100.dp)
                .clickable { onClick() },
            loading = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .size(100.dp)
                        .shimmerEffect()
                )
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.swvl_seeklogo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
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
        FromCairoSection() {}
    }
}


private val destinations = listOf(
    R.string.alex to 130 to "https://www.introducingegypt.com/f/egipto/egipto/galeria/big/alejandria.jpg",
    R.string.hurghada to 265 to "https://www.introducingegypt.com/f/egipto/egipto/galeria/big/hurghada.jpg",
    R.string.dahab to 340 to "https://images.unsplash.com/photo-1628238452386-b916aa9e4443?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80",
    R.string.mansura to 95 to "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Sunset_in_Mansoura.JPG/275px-Sunset_in_Mansoura.JPG",
    R.string.ismailia to 85 to "https://images.unsplash.com/photo-1664182770311-2781bcfc2ecc?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80",
    R.string.tanta to 75 to "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/Tanta-1.jpg/275px-Tanta-1.jpg",
).map {
    DestinationModel(location = it.first.first, price = it.first.second, imageUrl = it.second)
}

data class DestinationModel(
    @StringRes val location: Int,
    val price: Int,
    val imageUrl: String
)