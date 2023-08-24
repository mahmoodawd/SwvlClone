package com.example.swvlclone.ui.auth

import android.os.CountDownTimer
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.components.ForwardButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import com.example.swvlclone.ui.utils.rememberImeState
import kotlinx.coroutines.delay

@Composable
fun OTPScreen(
    phoneNumber: String,
    onBackPressed: () -> Unit,
) {
    val imeState by rememberImeState()
    val scrollState = rememberScrollState()
    var otp by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = imeState) {
        if (imeState) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
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
            length = 4,
            value = otp,
            requestFocus = true,
            visualTransformation = VisualTransformation.None,
            clearFocusWhenFilled = false,
            onValueChange = {
                otp = it
            })
        OtpSentStatusText() {}
        BottomSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onForwardClick = {
                //Take the otp Value and check if correct
            }, onHelpClick = {})
    }
}

@Composable
fun OtpSentStatusText(
    modifier: Modifier = Modifier,
    onTimesUp: () -> Unit = {}
) {
    var timeOutValue by remember { mutableIntStateOf(5) }
        LaunchedEffect(Unit) {
            while (timeOutValue > 0) {
                delay(1000)
                timeOutValue--
            }
        }
        val animatedValue by animateIntAsState(
            targetValue = timeOutValue,
            animationSpec = tween(durationMillis = timeOutValue * 1000),
            label = ""
        )
        val isTimeLeft = animatedValue > 0
        val msg =
            if (isTimeLeft) "Resend Code in $animatedValue Seconds" else "Resend verification Code"
    Text(
        text = msg,
        color = if (isTimeLeft) MaterialTheme.colorScheme.outline else Color.Blue,
        fontSize = 18.sp,
        modifier = modifier.clickable(!isTimeLeft) {
            onTimesUp()
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
        OTPScreen("01141680631") {}
    }
}