package com.example.swvlclone.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.swvlclone.ui.navigation.SwvlCloneDestination
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun SwvlCloneNavigationDrawer(
    items: List<SwvlCloneDestination>,
    onItemClick: (String) -> Unit,
    currentDestination: String,
    userName: String,
    userPhotoUrl: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DrawerHeader(
            title = userName,
            photo = userPhotoUrl,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        items.forEach { item ->
            val selected = item.route == currentDestination
            DrawerItem(
                selected = selected,
                onItemClick = { onItemClick(item.route) },
                itemTitle = item.name
            )

            Spacer(modifier = Modifier.height(20.dp))

        }

    }
}

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    title: String,
    photo: String
) {
    Box(
        modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp)),
        Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, end = 20.dp)
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = photo,
                contentDescription = title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
                    .padding(8.dp),
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun DrawerItem(
    onItemClick: () -> Unit,
    selected: Boolean = false,
    itemTitle: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onItemClick()
            },
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp),
            text = itemTitle,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (selected) MaterialTheme.colorScheme.primary else Color.Black
        )
    }
}

@Preview
@Composable
private fun UserAvatarPreview() {
    SwvlCloneTheme {
        Button(onClick = { /*TODO*/ }) {

        }
        DrawerHeader(
            title = "UserName",
            photo = "",
        )
    }
}