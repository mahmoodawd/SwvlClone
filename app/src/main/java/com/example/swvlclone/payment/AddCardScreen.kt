package com.example.swvlclone.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ModeEditOutline
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.components.TextWithLeadingIcon
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun AddCardRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    AddCardScreen(
        onBackPressed = onBackPressed,
        onAddCardClick = {},
        modifier = modifier
    )
}

@Composable
fun AddCardScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onAddCardClick: () -> Unit,
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
            text = stringResource(id = R.string.add_card),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        CardDetailInputField(text = "Card Number", length = 16, modifier = Modifier.fillMaxWidth())

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CardDetailInputField(
                text = "Expiry Date",
                length = 4,
                modifier = Modifier.weight(0.55f)
            )
            CardDetailInputField(text = "CVV", length = 3, modifier = Modifier.weight(0.45f))
        }
        CardDetailInputField(text = "Card Holder Name", modifier = Modifier.fillMaxWidth())
        TextWithLeadingIcon(
            text = "Your payment information will be stored securely",
            icon = Icons.Default.VerifiedUser,
            color = Color(0xFF009d56),
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = onAddCardClick
        ) {
            Text(
                text = stringResource(id = R.string.add_card).uppercase(),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun CardDetailInputField(
    modifier: Modifier = Modifier,
    text: String,
    length: Int,
) {
    var value by remember { mutableStateOf(text) }
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ModeEditOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        },
        placeholder = { Text(text = value) },
        onValueChange = { value = if (it.length >= length) it.substring(0 until length) else it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .padding(vertical = 4.dp)
    )
}

@Composable
fun CardDetailInputField(
    modifier: Modifier = Modifier,
    text: String,
) {
    var value by remember { mutableStateOf(text) }
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ModeEditOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        },
        placeholder = { Text(text = value) },
        onValueChange = { value = it },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .padding(vertical = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun AddCardPreview() {
    SwvlCloneTheme {
        AddCardScreen(
            onBackPressed = {},
            onAddCardClick = {}
        )
    }
}