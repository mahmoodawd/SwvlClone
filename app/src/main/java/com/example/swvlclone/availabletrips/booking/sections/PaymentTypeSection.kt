package com.example.swvlclone.availabletrips.booking.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PaymentTypeSection(
    modifier: Modifier = Modifier,
    onclick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface
            )
            .clickable { onclick() }
    ) {
        Icon(
            imageVector = Icons.Default.Money,
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = "Cash",
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Go",
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        )

    }

}