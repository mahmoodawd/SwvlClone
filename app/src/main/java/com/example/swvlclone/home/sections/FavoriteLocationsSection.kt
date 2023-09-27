package com.example.swvlclone.home.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.comon.composeext.shimmerEffect
import com.example.swvlclone.comon.snackbar.SnackbarManager
import com.example.swvlclone.comon.utils.HOME
import com.example.swvlclone.comon.utils.WORK
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.home.UserLocationUiState
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun FavoriteLocationSection(
    modifier: Modifier = Modifier,
    userLocationsState: UserLocationUiState,
    onFavoriteLocationClick: (Boolean, String, TripLocation) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface
            ),
    ) {
        var userHomeLocation: UserLocation? = null
        var userWorkLocation: UserLocation? = null
        when (userLocationsState) {
            is UserLocationUiState.Success -> {
                userHomeLocation = userLocationsState.locations.firstOrNull { it.type == HOME }
                userWorkLocation = userLocationsState.locations.firstOrNull { it.type == WORK }
            }

            is UserLocationUiState.Error -> {
                SnackbarManager.showMessage(R.string.error)
            }

            else -> {}
        }

        FavoriteLocationItem(
            icon = Icons.Default.Home,
            text = R.string.add_home,
            title = userHomeLocation?.title ?: "",
            locationSet = userHomeLocation != null,
            showShimmerEffect = userLocationsState is UserLocationUiState.Loading,
            onClick = {
                onFavoriteLocationClick(
                    userHomeLocation != null,
                    HOME,
                    TripLocation(
                        "", userHomeLocation?.title ?: ""
                    )
                )
            }
        )
        Divider(modifier = Modifier.padding(start = 32.dp))
        FavoriteLocationItem(
            icon = Icons.Default.Work,
            text = R.string.add_work,
            title = userWorkLocation?.title ?: "",
            locationSet = userWorkLocation != null,
            showShimmerEffect = userLocationsState is UserLocationUiState.Loading,
            onClick = {
                onFavoriteLocationClick(
                    userWorkLocation != null, WORK,
                    TripLocation(
                        "", userWorkLocation?.title ?: ""
                    )
                )
            }

        )
    }
}

@Composable
private fun FavoriteLocationItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    title: String,
    @StringRes text: Int,
    locationSet: Boolean = false,
    showShimmerEffect: Boolean
) {
    if (showShimmerEffect) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(.25.dp),
                modifier = Modifier
                    .padding(start = 16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(20.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .shimmerEffect()
                )
            }
        }
    } else {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
                .clickable { onClick() }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(.25.dp),
                modifier = Modifier
                    .padding(start = 16.dp),
            ) {
                Text(
                    //Here we remove the first word if Location is set
                    text = if (locationSet) stringResource(id = text)
                        .replaceFirst(Regex("\\b\\w+\\b"), "").trim() else
                        stringResource(id = text)
                )
                if (locationSet) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (locationSet) Icons.Default.KeyboardArrowRight else Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteLocationsSectionPreview() {
    SwvlCloneTheme {
        FavoriteLocationSection(
            userLocationsState =
            UserLocationUiState.Success(
                listOf(
                    UserLocation(1.5, 2.3, "Where I live", HOME),
                    UserLocation(1.5, 2.3, "Where I work", WORK),
                )
            ),
            onFavoriteLocationClick = { _, _, _ -> }
        )
    }
}
