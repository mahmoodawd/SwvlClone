package com.example.swvlclone.usertrips.details.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.UTurnRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit

) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = modifier
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.car_roof), contentDescription = null,
            tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 32.dp)
                .size(56.dp)
                .align(Alignment.Start)
        )
        ButtonsSection(
            onCallClick = {},
            onToStationClick = {},
            onCancelClick = onCancelClick
        )
        ReferSection()
    }
}

@Composable
private fun ButtonsSection(
    modifier: Modifier = Modifier,
    onCallClick: () -> Unit,
    onToStationClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ButtonWithTextBelow(
            onClick = onCallClick,
            icon = Icons.Default.Phone,
            title = R.string.call,
            selected = true,
        )
        ButtonWithTextBelow(
            onClick = onToStationClick,
            icon = Icons.Default.UTurnRight,
            title = R.string.to_station
        )
        ButtonWithTextBelow(
            onClick = onCancelClick,
            icon = Icons.Default.Close,
            title = R.string.cancel_trip
        )

    }
}

@Composable
fun ReferSection(modifier: Modifier = Modifier) {
    val amount = 10
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp) // Add padding to exclude the top side shadow
            .shadow(
                shape = RoundedCornerShape(16.dp),
                elevation = 4.dp,
                clip = true,
                ambientColor = MaterialTheme.colorScheme.outline
            )
            .background(Color.White)
            .clip(RoundedCornerShape(16.dp))
            .padding(top = 16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(100.dp)
        ) {
            Text(
                text = "Earn $amount EGP credits each time \n you refer a Friend",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Button(onClick = { }, modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = "Refer Now".uppercase(),
                    letterSpacing = 2.sp
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.CardGiftcard,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clip(CircleShape)
                .padding(16.dp)
                .size(56.dp)
                .align(Alignment.CenterVertically)
        )
    }

}

@Composable
fun ButtonWithTextBelow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    @StringRes title: Int,
    selected: Boolean = false,
) {

    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(64.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon, contentDescription = stringResource(title),
            tint = if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .border(
                    width = 1.dp,
                    color = if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.outline.copy(
                        alpha = 0.5f
                    ),
                    shape = CircleShape
                )
                .background(if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .clickable { onClick() }
        )
        Text(
            text = stringResource(title),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopSectionPreview() {
    SwvlCloneTheme {
        TopSection(onCancelClick = {})
    }
}