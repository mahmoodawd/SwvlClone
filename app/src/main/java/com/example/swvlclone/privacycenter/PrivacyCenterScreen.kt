package com.example.swvlclone.privacycenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme


@Composable
fun PrivacyCenterRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    PrivacyCenterScreen(
        onBackPressed = onBackPressed,
        modifier = modifier
    )
}

@Composable
fun PrivacyCenterScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackButton(
            modifier = Modifier.align(Alignment.Start),
            onClick = onBackPressed
        )
        Text(
            text = stringResource(id = R.string.privacy_center),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        PrivacyCenterItem(text = "Terms and Conditions") {}
        Divider()
        PrivacyCenterItem(text = "Privacy Policy") {}
        Divider()
        PrivacyCenterItem(text = "Account Actions") {}
    }
}

@Composable
fun PrivacyCenterItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrivacyCenterPreview() {
    SwvlCloneTheme {
        PrivacyCenterScreen {}
    }
}