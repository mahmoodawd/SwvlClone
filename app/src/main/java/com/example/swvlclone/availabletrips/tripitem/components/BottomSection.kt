package com.example.swvlclone.availabletrips.tripitem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    busType: String,
    oldPrice: Int,
    newPrice: Int,
) {


    val annotatedText = buildAnnotatedString {
        val activeStyle = MaterialTheme.typography.labelSmall.copy(
            color = Color(0xFF59BF92),
            fontWeight = FontWeight.Bold,
        ).toSpanStyle()
        val enActiveStyle = MaterialTheme.typography.labelSmall.copy(
            color = MaterialTheme.colorScheme.outline
        ).toSpanStyle()
        withStyle(style = if (busType == "Premium") activeStyle else enActiveStyle) {
            append("Premium")
        }
        withStyle(style = if (busType == "Bus") activeStyle else enActiveStyle) {
            append(" • Bus")
        }
        withStyle(style = if (busType == "AC") activeStyle else enActiveStyle) {
            append(" • AC")
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = annotatedText,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.surface
                )
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$oldPrice EGP",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            textDecoration = TextDecoration.LineThrough,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = "$newPrice EGP",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF009e57),

            )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun BottomSectionPreview() {
    SwvlCloneTheme {
        BottomSection(busType = "Bus", oldPrice = 80, newPrice = 120)
    }

}