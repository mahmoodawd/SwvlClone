package com.example.swvlclone.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swvlclone.R
import com.example.swvlclone.comon.utils.WORK
import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun SaveLocationRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    locationType: String,
    onBackPressed: () -> Unit,
) {
    SaveLocationScreen(
        onBackPressed = onBackPressed,
        onLocationSearchItemClick = {
            viewModel.addNewLocation(it)
            onBackPressed()
        }, locationType = locationType,
        modifier = modifier
    )
}

@Composable
fun SaveLocationScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onLocationSearchItemClick: (UserLocation) -> Unit,
    locationType: String
) {
    var displaySearchResult by remember {
        mutableStateOf(false)
    }
    var displayMap by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        BackButton(onClick = onBackPressed)
        LocationInputField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onValueChanged = {
                displaySearchResult = true
                //TODO Search for the typed location
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (!displaySearchResult) {
                TextButton(
                    contentPadding = PaddingValues(8.dp),
                    onClick = { displayMap = true }
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                    Text(text = stringResource(R.string.set_location_on_map))
                }
            }
            Divider(modifier = Modifier.align(Alignment.BottomCenter))

            if (displaySearchResult) {
                LazyColumn() {
                    items(searchResultList) {
                        LocationResultItem(title = it.title, subtitle = it.subtitle) {
                            // TODO save the selected location with its type
                            onLocationSearchItemClick(
                                UserLocation(
                                    lat = 1.5,
                                    lon = 1.3,
                                    title = it.title,
                                    type = locationType
                                )
                            )
                        }
                    }
                }
            }

            if (displayMap) {
                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                /*GoogleMap(
                    modifier = Modifier.matchParentSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .padding(horizontal = 16.dp),
                        onClick = { *//*TODO*//* }) {
                        Text(text = stringResource(R.string.add))

                    }*/
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter),
                    onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.add))

                }
            }
        }
    }

}


@Composable
fun LocationResultItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(.25.dp),
            modifier = Modifier
                .padding(start = 16.dp),
        ) {
            Text(
                text = title
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
            )
            Divider(
                Modifier
                    .background(MaterialTheme.colorScheme.outline)
            )
        }
    }
}

@Composable
private fun LocationInputField(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    var location by remember { mutableStateOf("") }

    val backgroundColor =
        MaterialTheme.colorScheme.background
    val borderColor = MaterialTheme.colorScheme.surface
    val roundedCornerShape = RoundedCornerShape(8.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 16.dp)
            .border(shape = roundedCornerShape, color = borderColor, width = 2.dp)
            .shadow(shape = roundedCornerShape, elevation = 3.dp)
            .background(shape = roundedCornerShape, color = backgroundColor)
    ) {
        Icon(
            imageVector = Icons.Default.Brightness1,
            contentDescription = stringResource(id = R.string.where_to),
            tint = Color.Blue,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
                .shadow(shape = CircleShape, elevation = 10.dp)
        )
        TextField(
            value = location,
            singleLine = true,
            placeholder = { Text(stringResource(R.string.where_to)) },
            onValueChange = {
                location = it
                onValueChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(vertical = 4.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun SaveLocationPreview() {
    SwvlCloneTheme {
        SaveLocationScreen(
            onBackPressed = { },
            onLocationSearchItemClick = {},
            locationType = WORK
        )
    }
}


private val searchResultList = listOf(
    SearchResultLocation("Fayda Kamel", "Al Basatin Al Gharbeyah, El Basatin, Egypt"),
    SearchResultLocation("Fayoum", "Al Faiyum Governorate Desert, Egypt"),
    SearchResultLocation("Fayed Abd El-Hameed", "Oula Al Haram, El Omraniya, Egypt")
)

data class SearchResultLocation(
    val title: String,
    val subtitle: String
)
