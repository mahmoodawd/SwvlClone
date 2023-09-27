package com.example.swvlclone.location.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R

@Composable
fun MapSection(
    modifier: Modifier = Modifier,
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
            imageVector = Icons.Default.PinDrop,
            contentDescription = stringResource(id = R.string.set_location_on_map),
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        )

        Text(
            text = stringResource(id = R.string.set_location_on_map),
            fontWeight = FontWeight.Bold
        )
    }
}
