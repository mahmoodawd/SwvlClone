package com.example.swvlclone.auth

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    onPhoneFieldClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.traffic),
            contentDescription = null,
            alignment = TopCenter,
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.White, blendMode = BlendMode.Overlay),
            alpha = .50f,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .clip(RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier
                .align(BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.swvl_seeklogo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 4.dp)
                    .align(CenterHorizontally),
                contentScale = ContentScale.Fit
            )
            Text(
                text = stringResource(R.string.go_places),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )
            Text(
                text = stringResource(R.string.enter_your_mobile_number),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        onPhoneFieldClick()
                    }
            )
            SocialSection()
        }
    }
}


@Composable
fun SocialSection(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.or_connect_with_social),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SocialButton(label = R.string.facebook, icon = R.drawable.facebook)
            SocialButton(label = R.string.google, icon = R.drawable.google)
        }
    }
}

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(),
        onClick = { onClick() }) {
        Image(
            painter = painterResource(icon),
            contentDescription = stringResource(id = label),
            modifier = Modifier
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = stringResource(id = label),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall
        )

    }
}

@Preview(device = "spec:width=1080px,height=2340px,dpi=440", showBackground = true)
@Composable
fun AuthScreenPreview() {
    SwvlCloneTheme {
        AuthScreen() {}
    }
}