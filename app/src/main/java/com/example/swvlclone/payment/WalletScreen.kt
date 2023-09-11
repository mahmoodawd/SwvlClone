package com.example.swvlclone.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme


@Composable
fun WalletRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onAddCreditClick: () -> Unit,
) {
    WalletScreen(
        onBackPressed = onBackPressed,
        onAddCreditClick = onAddCreditClick,
        modifier = modifier
    )
}

@Composable
fun WalletScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onAddCreditClick: () -> Unit,
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
            text = stringResource(id = R.string.wallet),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        Spacer(modifier = Modifier.weight(0.20f))
        Text(
            text = "Your Credit",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        WalletBalanceSection(balance = 0f)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            onClick = onAddCreditClick
        ) {
            Text(
                text = stringResource(id = R.string.add_credit).uppercase(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Composable
fun WalletBalanceSection(
    modifier: Modifier = Modifier,
    balance: Float,
) {
    val balanceAnnotatedString = buildAnnotatedString {
        withStyle(
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold
            ).toSpanStyle()
        ) {
            append(balance.toString())
        }
        withStyle(
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.outline
            ).toSpanStyle()
        ) {
            append(" EGP")
        }
    }

    Text(
        text = balanceAnnotatedString,
        fontSize = 24.sp,
        modifier = modifier
            .padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun WalletPreview() {
    SwvlCloneTheme {
        WalletScreen(onBackPressed = {}) {}
    }

}