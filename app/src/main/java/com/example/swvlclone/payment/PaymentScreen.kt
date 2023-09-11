package com.example.swvlclone.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun PaymentRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onAddCreditClick: () -> Unit
) {
    PaymentScreen(
        onBackPressed = onBackPressed,
        onAddCreditClick = onAddCreditClick,
        modifier = modifier
    )
}

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onAddCreditClick: () -> Unit
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
            text = stringResource(id = R.string.payment_methods),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        Row(
            modifier = modifier.padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.money),
                contentDescription = "cash",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp),
            )

            Text(
                text = "Cash",
                fontSize = 18.sp
            )
        }
        Text(
            text = "Add a credit or debit card",
            color = Color.Blue,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { onAddCreditClick() })
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentPreview() {
    SwvlCloneTheme {
        PaymentScreen(
            onBackPressed = {},
            onAddCreditClick = {}
        )
    }
}