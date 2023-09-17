package com.example.swvlclone.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.swvlclone.R
import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val prefs = UserPrefs(
    avatar = "",
    name = "Mahmoud",
    mobile = "01125463987",
    email = "mahmoud@gmail.com",
    city = "Cairo",
    language = "English",
    socialAccounts = listOf("Facebook, Google"),
)
const val SIGN_OUT_SHEET = "SignOut"
const val CHANGE_EMAIL_SHEET = "ChangeEmail"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onCityClick: (String) -> Unit,
    onLanguageClick: (String) -> Unit,
    onConnectedAccountsClick: (List<String>) -> Unit,
    googleAuthUiClient: AuthUiClient,
    onSignOut: () -> Unit,
) {
    val sheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleScope = lifecycleOwner.lifecycleScope

    val coroutineScope = rememberCoroutineScope()
    SettingsScreen(
        userPrefs = prefs.apply {
            val userData = googleAuthUiClient.getSignedInUser()
            name = userData?.userName ?: "NO_Name"
            email = userData?.email ?: "NO_Email"
            avatar = userData?.profilePhotoUrl ?: ""
        },
        onBackPressed = onBackPressed,
        onCityClick = onCityClick,
        onLanguageClick = onLanguageClick,
        onConnectedAccountsClick = onConnectedAccountsClick,
        modifier = modifier,
        sheetState = sheetState,
        coroutineScope = coroutineScope,
        onChangeEmailSubmit = {},
        onSignOut = {
            lifecycleScope.launch { googleAuthUiClient.signOut() }
            onSignOut()
        }

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
    onSignOut: () -> Unit
) {
    var sheetType by remember {
        mutableStateOf(CHANGE_EMAIL_SHEET)
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            when (sheetType) {
                CHANGE_EMAIL_SHEET -> {
                    ChangeEmailSheetContent(value = prefs.email, onSubmit = onChangeEmailSubmit)
                }

                SIGN_OUT_SHEET -> {
                    SignOutSheetContent(onSignOut = onSignOut)
                }
            }
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
            UserNameSection(userName = prefs.name, userImageUrl = prefs.avatar)
            SettingTile(title = R.string.phone, subtitle = userPrefs.mobile)
            SettingTile(
                title = R.string.email,
                subtitle = userPrefs.email,
                onClick = {
                    sheetType = CHANGE_EMAIL_SHEET
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
                    sheetType = SIGN_OUT_SHEET
                    coroutineScope.launch { sheetState.show() }
                }
            ) {
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
    userImageUrl: String
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
            SubcomposeAsyncImage(
                model = userImageUrl,
                contentDescription = userName,
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
           /* Icon(
                imageVector = avatar, contentDescription = "User Image",
                modifier = Modifier.size(48.dp)
            )*/
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
            onSignOut = {},
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
            coroutineScope = rememberCoroutineScope()
        )
    }
}

@Composable
fun SignOutSheetContent(
    modifier: Modifier = Modifier,
    onSignOut: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sign_out).plus("?"),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = onSignOut
        ) {
            Text(
                text = stringResource(id = R.string.sign_out).uppercase(),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

data class UserPrefs(
    var avatar: String,
    var name: String,
    val mobile: String,
    var email: String,
    val city: String,
    val language: String,
    val socialAccounts: List<String>,
)
