package com.example.swvlclone.settings

import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val prefs = UserPrefs(
    name = "Mahmoud",
    mobile = "01125463987",
    email = "mahmoud@gmail.com",
    city = "Cairo",
    language = "English",
    socialAccounts = listOf("Facebook, Google"),
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onCityClick: (String) -> Unit,
    onLanguageClick: (String) -> Unit,
    onConnectedAccountsClick: (List<String>) -> Unit,
) {
    val sheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()
    SettingsScreen(
        userPrefs = prefs,
        onBackPressed = onBackPressed,
        onCityClick = onCityClick,
        onLanguageClick = onLanguageClick,
        onConnectedAccountsClick = onConnectedAccountsClick,
        modifier = modifier,
        sheetState = sheetState,
        coroutineScope = coroutineScope,
        onChangeEmailSubmit = {}
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    userPrefs: UserPrefs,
    onChangeEmailSubmit: (String) -> Unit,
    onCityClick: (String) -> Unit,
    onLanguageClick: (String) -> Unit,
    onConnectedAccountsClick: (List<String>) -> Unit,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
) {

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            ChangeEmailSheetContent(value = prefs.email, onSubmit = onChangeEmailSubmit)
        }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            SwvlCloneTopBar(
                title = stringResource(R.string.settings),
                icon = R.drawable.arrow_back,
                onIconClick = onBackPressed
            )
            UserNameSection(userName = prefs.name, avatar = Icons.Rounded.Person)
            SettingTile(title = R.string.phone, subtitle = userPrefs.mobile)
            SettingTile(
                title = R.string.email,
                subtitle = userPrefs.email,
                onClick = {
                    coroutineScope.launch { sheetState.show() }
                })
            SettingTile(
                title = R.string.city,
                subtitle = userPrefs.city,
                onClick = { onCityClick(userPrefs.city) })
            SettingTile(
                title = R.string.language,
                subtitle = userPrefs.language,
                onClick = { onLanguageClick(userPrefs.language) }
            )
            SettingTile(
                title = R.string.social_accounts,
                icon = Icons.Outlined.KeyboardArrowRight,
                subtitle = stringResource(R.string.connected_accounts),
                onClick = { onConnectedAccountsClick(userPrefs.socialAccounts) }
            )
            Divider()
            TextButton(
                onClick = {
                    //TODO Sign the user out
                }) {
                Text(
                    text = stringResource(id = R.string.sign_out),
                    color = MaterialTheme.colorScheme.outline
                )
            }

        }
    }

}

@Composable
fun UserNameSection(
    modifier: Modifier = Modifier,
    userName: String,
    avatar: ImageVector
) {
    Box(
        modifier
            .height(120.dp)
            .fillMaxWidth()
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
            Icon(
                imageVector = avatar, contentDescription = "User Image",
                modifier = Modifier.size(48.dp)
            )
            Column {
                Text(
                    text = stringResource(R.string.name),
                    maxLines = 1,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = userName,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}

@Composable
fun SettingTile(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    icon: ImageVector = Icons.Outlined.Edit,
    subtitle: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(title),
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.align(Alignment.Start)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
        ) {
            Text(
                text = subtitle,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SettingTile(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    subtitle: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(title),
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.align(Alignment.Start)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = subtitle,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun SettingsPreview() {
    SwvlCloneTheme {
        SettingsScreen(
            userPrefs = prefs,
            onBackPressed = {},
            onCityClick = {},
            onLanguageClick = {},
            onConnectedAccountsClick = {},
            onChangeEmailSubmit = {},
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
            coroutineScope = rememberCoroutineScope()
        )
    }
}

data class UserPrefs(
    val name: String,
    val mobile: String,
    val email: String,
    val city: String,
    val language: String,
    val socialAccounts: List<String>,
)
