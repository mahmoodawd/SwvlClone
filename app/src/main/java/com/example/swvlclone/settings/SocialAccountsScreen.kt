package com.example.swvlclone.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
fun SocialAccountsRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    connectedAccounts: List<String>
) {
    SocialAccountsScreen(
        onBackPressed = onBackPressed,
        connectedAccounts = connectedAccounts,
        modifier = modifier
    )
}

@Composable
fun SocialAccountsScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    connectedAccounts: List<String>,
    onToggled: (Boolean) -> Unit = {},
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
            text = stringResource(id = R.string.social_accounts),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
//        connectedAccounts.forEach {}
        SocialToggleButton(
            title = R.string.facebook,
            checked = false,
            onCheckedChange = {}
        )
        Divider()
        SocialToggleButton(
            title = R.string.google,
            checked = true,
            onCheckedChange = {}
        )
        Divider()

    }
}

@Composable
private fun SocialToggleButton(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = title))
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = checked, onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(uncheckedTrackColor = MaterialTheme.colorScheme.background)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SocialAccountsPreview() {
    SwvlCloneTheme {

        SocialAccountsScreen(
            onBackPressed = { },
            connectedAccounts = listOf("Facebook, Google")
        )
    }
}