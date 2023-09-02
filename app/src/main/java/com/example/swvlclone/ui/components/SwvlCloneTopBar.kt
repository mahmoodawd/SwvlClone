package com.example.swvlclone.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme


@Composable
fun SwvlCloneTopBar(
    title: String,
    subtitle: String? = null,
    @DrawableRes icon: Int,
    onIconClick: () -> Unit = {}
) {
    TopAppBar(
        title = { AppBarTitle(title = title, subtitle = subtitle ?: "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { onIconClick() }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Drawer Icon",
                    modifier = Modifier
                        .size(42.dp)
                        .shadow(shape = CircleShape, elevation = 8.dp, clip = true)
                        .background(Color.White)
                        .clip(CircleShape)
                        .padding(4.dp)
                )

            }
        }
    )
}

@Composable
fun AppBarTitle(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
//        modifier = modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        if (subtitle.isNotBlank()) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwvlTopBarPreview() {
    SwvlCloneTheme {
        SwvlCloneTopBar(
            title = "Hey, User",
            subtitle = "Where are you going?",
            icon = R.drawable.burger_menu_left
        )
    }
}