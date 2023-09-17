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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun AuthMainRoute(
    modifier: Modifier = Modifier,
    onPhoneFieldClick: () -> Unit,
    onGoogleSignInSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
    googleAuthUiClient: AuthUiClient
) {
    val authUiState by viewModel.uiState
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleScope = lifecycleOwner.lifecycleScope
    
//What happens after login
    LaunchedEffect(key1 = authUiState.isSuccessful) {
        if (authUiState.isSuccessful) {
            Timber.i("SignIn Successful")
            onGoogleSignInSuccess()
            viewModel.resetState()
        }
    }
    val oneTapState = rememberOneTapSignInState()
    OneTapSignInWithGoogle(
        state = oneTapState,
        clientId = stringResource(id = R.string.default_web_client_id),
        onTokenIdReceived = { tokenId ->
            Timber.d("LOG: Token Received: $tokenId")
            lifecycleScope.launch {
                val signInResult = googleAuthUiClient.signInWithIdToken(tokenId)
                viewModel.onSignInResult(signInResult)
            }
        },
        onDialogDismissed = { message ->
            Timber.d("LOG: DIALOG DISMISSED $message")
        }
    )

    AuthScreen(
        onPhoneFieldClick = onPhoneFieldClick,
        modifier = modifier,
        state = authUiState,
        onGoogleSignIn = { oneTapState.open() }
    )
}

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    onPhoneFieldClick: () -> Unit,
    state: SignInState,
    onGoogleSignIn: () -> Unit
) {

    LaunchedEffect(key1 = state.isError) {
        Timber.e(state.isError)
        //Should Display Snackbar with the error
    }
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
            SocialSection(
                onGoogleSignIn = onGoogleSignIn,
                onFacebookSignIn = {}
            )
        }
    }
}


@Composable
fun SocialSection(
    modifier: Modifier = Modifier,
    onGoogleSignIn: () -> Unit,
    onFacebookSignIn: () -> Unit,
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
            SocialButton(
                label = R.string.facebook,
                icon = R.drawable.facebook,
                onClick = onFacebookSignIn
            )
            SocialButton(
                label = R.string.google,
                icon = R.drawable.google,
                onClick = onGoogleSignIn
            )
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
        onClick = onClick
    ) {
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
        AuthScreen(
            onPhoneFieldClick = {},
            onGoogleSignIn = {},
            state = SignInState()
        )
    }
}