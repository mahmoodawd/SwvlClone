package com.example.swvlclone.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun ChangeEmailSheetContent(
    modifier: Modifier = Modifier,
    value: String,
    onSubmit: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.your_email),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        EmailField(value = value)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = { onSubmit("") }
        ) {
            androidx.compose.material.Text(
                text = stringResource(id = R.string.submit).uppercase(),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    value: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp) // Add padding to exclude the top side shadow
            .shadow(
                shape = RoundedCornerShape(16.dp),
                elevation = 4.dp,
                clip = true,
                ambientColor = MaterialTheme.colorScheme.outline
            )
            .background(Color.White)
            .clip(RoundedCornerShape(16.dp))
            .padding(4.dp)
    ) {
        var text by remember { mutableStateOf(value) }

        TextField(
            value = text,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline,
            ),
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(stringResource(id = R.string.email)) },
            trailingIcon = {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable { text = "" })
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangeEmailPreview() {
    SwvlCloneTheme {
        ChangeEmailSheetContent(value = "mahmoud@gmail.com", onSubmit = {})
    }
}