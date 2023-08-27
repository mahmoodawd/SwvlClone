package com.example.swvlclone.ui.home.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun FavoriteLocationSection(
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
        FavoriteLocationItem(icon = Icons.Default.Home, text = R.string.add_home)
        Divider(modifier = Modifier.padding(start = 32.dp))
        FavoriteLocationItem(icon = Icons.Default.Work, text = R.string.add_work)
    }
}

@Composable
private fun FavoriteLocationItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    locationSet: Boolean = false,
    @StringRes text: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = text),
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
        )
        Text(
            text = stringResource(id = text),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
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

@Preview(showBackground = true)
@Composable
private fun FavoriteLocationsSectionPreview() {
    SwvlCloneTheme {
        FavoriteLocationSection()
    }
}

@Preview
@Composable
private fun FavoriteLocationItemPreview() {
    SwvlCloneTheme {
        FavoriteLocationItem(
            icon = Icons.Default.Home,
            text = R.string.add_home
        )
    }
}