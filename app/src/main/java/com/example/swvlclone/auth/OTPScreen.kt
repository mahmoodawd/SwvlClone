package com.example.swvlclone.auth

import android.app.Activity
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.swvlclone.R
import com.example.swvlclone.comon.snackbar.SnackbarManager
import com.example.swvlclone.comon.snackbar.SnackbarMessage
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.components.ForwardButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun OtpRoute(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onBackPressed: () -> Unit,
    onSignInSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {

    val scrollState = rememberScrollState()
    val authUiState by viewModel.uiState
    val activity = LocalContext.current as Activity
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val mobileAuthUiClient = MobileAuthUiClient()

    OTPScreen(
        phoneNumber = phoneNumber,
        onBackPressed = onBackPressed,
        onForwardClick = { otp ->
            lifecycleScope.launch {
                val signInResult = mobileAuthUiClient.verifyPhoneNumberWithCode(
                    activity,
                    MobileAuthUiClient.storedVerificationId!!,
                    otp
                )
                viewModel.onSignInResult(signInResult)
            }
        },
        onSignInSuccess = {
            onSignInSuccess()
            viewModel.resetState()
        },
        onSignInError = {
            SnackbarManager.showMessage(
                SnackbarMessage.StringSnackbar(
                    authUiState.isError?.substringBefore(
                        ','
                    ) ?: "Error"
                )
            )
            viewModel.resetState()
        },
        codeTimeOutValue = OTP_TIME_OUT,
        onResendCodeClick = {
            mobileAuthUiClient.startPhoneNumberVerification(
                phoneNumber,
                activity,
                OTP_TIME_OUT
            )
        },
        scrollState = scrollState,
        signInState = authUiState,
        modifier = modifier,
    )
}

@Composable
fun OTPScreen(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onBackPressed: () -> Unit,
    onForwardClick: (String) -> Unit,
    onSignInSuccess: () -> Unit,
    onSignInError: () -> Unit,
    onResendCodeClick: () -> Unit,
    codeTimeOutValue: Int,
    scrollState: ScrollState,
    signInState: SignInState,
) {

    var otp by remember {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    
    LaunchedEffect(
        key1 = signInState.isSuccessful,
        key2 = signInState.isError != null,
    ) {
        isLoading = false
        if (signInState.isSuccessful) {
            onSignInSuccess()
        }
        if (signInState.isError != null) {
            onSignInError()
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            BackButton(
                modifier = Modifier
                    .align(Alignment.Start)
            ) { onBackPressed() }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(
                    R.string.verify_your_number,
                ),
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Enter the verification code sent to $phoneNumber",
                softWrap = true,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OtpInputField(
                length = 6,
                value = otp,
                requestFocus = true,
                visualTransformation = VisualTransformation.None,
                clearFocusWhenFilled = false,
                onValueChange = {
                    otp = it
                })
            OtpSentStatusText(
                codeTimeOutValue = codeTimeOutValue,
                onResendCodeClick = onResendCodeClick
            )
            BottomSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onForwardClick = {
                    isLoading = true
                    onForwardClick(otp)
                }, onHelpClick = {})
        }
    }
}

@Composable
fun OtpSentStatusText(
    modifier: Modifier = Modifier,
    codeTimeOutValue: Int,
    onResendCodeClick: () -> Unit = {}
) {
    var timeLeft by rememberSaveable { mutableIntStateOf(codeTimeOutValue) }
    val timeIsUp = timeLeft == 0

    LaunchedEffect(timeLeft) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }
    val msg =
        if (timeIsUp) "Resend verification Code" else "Resend Code in $timeLeft Seconds"
    Text(
        text = msg,
        color = if (timeIsUp) Color.Blue else MaterialTheme.colorScheme.outline,
        fontSize = 16.sp,
        modifier = modifier.clickable(timeIsUp) {
            timeLeft = codeTimeOutValue
            onResendCodeClick()
        }
    )
}

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    onForwardClick: () -> Unit,
    onHelpClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        Text(
            text = "Need Help?",
            color = Color.Blue,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    onHelpClick()
                }
        )
        ForwardButton { onForwardClick() }
    }
}

@Preview(showBackground = true)
@Composable
fun OTPScreenPreview() {
    SwvlCloneTheme {
        OTPScreen(
            phoneNumber = "0141523698",
            onBackPressed = { },
            onForwardClick = { },
            scrollState = ScrollState(0),
            signInState = SignInState(),
            onSignInSuccess = {},
            onSignInError = {},
            onResendCodeClick = {},
            codeTimeOutValue = 10
        )
    }
}