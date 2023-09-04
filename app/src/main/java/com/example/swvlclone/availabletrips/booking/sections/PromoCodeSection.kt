package com.example.swvlclone.availabletrips.booking.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import java.util.Locale

@Composable
fun PromoCodeSection(
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    promoCodesCount: Int = 3
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onclick() }
    ) {
        Icon(
            imageVector = Icons.Default.CardGiftcard,
            contentDescription = "Promo Code",
            tint = Color.Blue,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
                .align(Alignment.Top)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(.25.dp),
            modifier = Modifier
                .padding(start = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.apply_promo_code).uppercase(Locale.ROOT),

                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )

            Text(
                text = "$promoCodesCount Promo Codes Available",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
            )


        }
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
