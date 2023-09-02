package com.example.swvlclone.home.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun OfferItem(
    modifier: Modifier = Modifier
) {
    val bgColor = Color(0xFF209BCD)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = bgColor, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "We Have Made the 'Extra \n Packages' Just for You!\n Click ->",
            softWrap = true,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.car_roof),
            contentDescription = "null",
            modifier = Modifier.size(85.dp)
        )
    }
}

@Preview
@Composable
private fun OfferItemPreview() {
    SwvlCloneTheme {
        OfferItem()
    }

}