package com.example.swvlclone.location.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R

@Composable
fun FavoriteLocationsSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface
            ),
    ) {
        FavoriteLocationItem(
            icon = Icons.Outlined.Home,
            title = R.string.add_home,
            subtitle = "There is My Home"
        )
        Divider(modifier = Modifier.padding(start = 32.dp))
        FavoriteLocationItem(
            icon = Icons.Outlined.WorkOutline,
            title = R.string.add_work
        )
        Divider(modifier = Modifier.padding(start = 32.dp))
        FavoriteLocationItem(
            icon = Icons.Outlined.LocationOn,
            title = R.string.saved_places,
            showNavIcon = true
        )
    }
}

@Composable
private fun FavoriteLocationItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    locationSet: Boolean = false,
    showNavIcon: Boolean = false,
    @StringRes title: Int,
    subtitle: String = "",
    onItemClick: () -> Unit = {},

    ) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = title),
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
                text = stringResource(id = title),
                fontWeight = FontWeight.Bold,
            )
            if (subtitle.isNotBlank()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline,
                )
            }

        }
        if (showNavIcon) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
            )
        }
    }
}
