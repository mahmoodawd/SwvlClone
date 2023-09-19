package com.example.swvlclone.auth

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canopas.campose.countrypicker.CountryPickerBottomSheet
import com.canopas.campose.countrypicker.localeToEmoji
import com.example.swvlclone.R
import com.example.swvlclone.comon.snackbar.SnackbarManager
import com.example.swvlclone.comon.utils.checkPhoneNumber
import com.example.swvlclone.comon.utils.getDefaultCountryCode
import com.example.swvlclone.comon.utils.getDefaultPhoneCode
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.components.ForwardButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import com.example.swvlclone.ui.transformation.PhoneNumberTransformation
import timber.log.Timber

const val OTP_TIME_OUT = 10

@Composable
fun MobileAuthRoute(
    onOtpSent: (String) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity
    val scrollState = rememberScrollState()
    var otpSentState by remember {
        mutableStateOf(false)
    }
    val mobileAuthUiClient = MobileAuthUiClient()


    MobileAuthScreen(
        onForwardButtonClick = { phoneNumber, isPhoneValid ->
            if (isPhoneValid) {
                mobileAuthUiClient
                    .startPhoneNumberVerification(phoneNumber, context,
                        codeTimeOutValue = OTP_TIME_OUT,
                        onCodeSent = {
                            onOtpSent(phoneNumber)
                        },
                        onVerificationFailed = {
                            SnackbarManager.showMessage(R.string.error)
                        }
                    )
            } else {
                SnackbarManager.showMessage(R.string.invalid_phone_number)
            }
        },
        onBackPressed = onBackPressed,
        scrollState = scrollState,
        context = context,
        onOtpSent = onOtpSent,
        otpSentState = otpSentState,
        modifier = modifier
    )
}

@Composable
fun MobileAuthScreen(
    modifier: Modifier = Modifier,
    context: Context,
    onForwardButtonClick: (String, Boolean) -> Unit,
    onBackPressed: () -> Unit,
    scrollState: ScrollState,
    onOtpSent: (String) -> Unit,
    otpSentState: Boolean = false
) {
    var isLoading by remember {
        mutableStateOf(false)
    }
    var fullPhoneNumber by remember {
        mutableStateOf("+16505556789")
    }
    var isPhoneValid by remember {
        mutableStateOf(false)
    }

    /*LaunchedEffect(otpSentState) {
        isLoading = false
        if (otpSentState) {
            onOtpSent(fullPhoneNumber)
        }
    }*/
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
       /* if (isLoading) {
            CircularProgressIndicator()
        }*/
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {

            BackButton(
                onClick = {
                    onBackPressed()
                },
                modifier = Modifier.align(Alignment.Start)
            )
            MobileNumberField(
                onValueChanged = { phoneNumber, isValid ->
                    fullPhoneNumber = phoneNumber
                    isPhoneValid = isValid
                }
            )
            TermsAndPrivacyText(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            ForwardButton(
                onClick = {
//                    isLoading = true
//                    if (isPhoneValid) {
                    onForwardButtonClick(fullPhoneNumber, isPhoneValid)
                    /*} else {
                        SnackbarManager.showMessage(R.string.invalid_phone_number)
                        isLoading = false
                    }*/
                },
                modifier = Modifier
                    .align(Alignment.End)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileNumberField(
    modifier: Modifier = Modifier,
    onValueChanged: (String, Boolean) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val hint = stringResource(R.string.enter_your_mobile_number)
        var phoneNumber by rememberSaveable { mutableStateOf("") }
        var openBottomSheet by rememberSaveable { mutableStateOf(false) }
        var countryDialCode by rememberSaveable { mutableStateOf(getDefaultPhoneCode(context)) }
        var countryCode by rememberSaveable { mutableStateOf(getDefaultCountryCode(context)) }
        Timber.i("LangCode: $countryCode")
        Text(
            text = localeToEmoji(countryCode) + countryDialCode,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { openBottomSheet = true }
        )
        TextField(
            value = phoneNumber,
            label = { Text(text = hint) },
            onValueChange = {
                phoneNumber = it
                val fullPhoneNumber = countryDialCode + phoneNumber
                val isValid = checkPhoneNumber(
                    phone = phoneNumber,
                    countryCode = countryCode,
                    fullPhoneNumber = fullPhoneNumber,
                )
                Timber.i("$fullPhoneNumber : $isValid")
                onValueChanged(fullPhoneNumber, isValid)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                autoCorrect = true
            ),
            visualTransformation = PhoneNumberTransformation(countryCode.uppercase()),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline,
            )
        )

        if (openBottomSheet) {
            CountryPickerBottomSheet(bottomSheetTitle = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = stringResource(R.string.select_country_text),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
                containerColor = Color.White,
                onItemSelected = {
                    countryDialCode = it.dial_code
                    countryCode = it.code
                    openBottomSheet = false
                },
                onDismissRequest = {
                    openBottomSheet = false
                })
        }
    }
}


@Composable
fun TermsAndPrivacyText(modifier: Modifier = Modifier) {
    val styleAsLink = MaterialTheme.typography.bodyLarge.copy(
        color = Color.Blue,
        textDecoration = TextDecoration.Underline
    )

    val annotatedText = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle()) {
            append("By Continuing, you agree to SWVLs ")
        }
        withStyle(style = styleAsLink.toSpanStyle()) {
            append("Terms")
        }
        append(" and ")
        withStyle(style = styleAsLink.toSpanStyle()) {
            append("Privacy")
        }
    }

    Text(
        text = annotatedText,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MobileAuthPreview() {
    SwvlCloneTheme {
        MobileAuthScreen(
            onForwardButtonClick = { _, _ -> },
            onBackPressed = { },
            context = LocalContext.current,
            scrollState = ScrollState(0),
            onOtpSent = {}
        )
    }

}
