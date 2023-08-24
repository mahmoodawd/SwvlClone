package com.example.swvlclone.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.components.ForwardButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import com.example.swvlclone.ui.utils.rememberImeState
import com.togitech.ccp.component.TogiCountryCodePicker

@Composable
fun MobileAuthScreen(
    onForwardButtonClick: (String) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    val context = LocalContext.current
    val imeState by rememberImeState()
    val scrollState = rememberScrollState()
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
        var fullPhoneNumber by remember {
            mutableStateOf("")
        }
        var isPhoneValid by remember {
            mutableStateOf(false)
        }
        BackButton(
            modifier = Modifier
                .align(Alignment.Start)
        ) { onBackPressed() }
        TogiCountryCodePicker(
            onValueChange = { (code, phone), isValid ->
                fullPhoneNumber = code + phone
                isPhoneValid = isValid
            },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
        )
        TermsAndPrivacyText(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        ForwardButton(
            onClick = {
                if (isPhoneValid) {
                    onForwardButtonClick(fullPhoneNumber)
                } else {
                    Toast.makeText(context, "Invalid Phone Number", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .align(Alignment.End)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileNumberField(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val hint = stringResource(R.string.enter_your_mobile_number)
        var phoneNumber by remember { mutableStateOf(hint) }
        val countryCode by remember {
            mutableStateOf("+1")
        }
        CountryItem()
        Text(
            text = countryCode,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        TextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Composable
private fun CountryItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = "Country Name", modifier = Modifier
                .offset(x = (-75).dp)
                .align(Alignment.Center)
        )
        Text(text = "CountryCode", modifier = Modifier.align(Alignment.CenterEnd))
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
        MobileAuthScreen()
    }

}
