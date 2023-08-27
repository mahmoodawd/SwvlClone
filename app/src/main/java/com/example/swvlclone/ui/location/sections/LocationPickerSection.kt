package com.example.swvlclone.ui.location.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.SwapVert
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R

@Composable
fun LocationPickerSection(
    modifier: Modifier = Modifier,
    onSwapClick: () -> Unit = {},
    onLocationPicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onLocationPicked() } // TODO To Be Deleted
        ) {
            LocationInputField(tint = Color.Red, placeHolder = R.string.from_where, selected = true)
            LocationInputField(tint = Color.Blue, placeHolder = R.string.where_to)
        }

        Icon(
            imageVector = Icons.Default.SwapVert,
            contentDescription = "Swap Locations",
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.CenterVertically)
                .padding(4.dp)
                .clickable { onSwapClick() }
        )
    }
}

@Composable
private fun LocationInputField(
    tint: Color,
    selected: Boolean = false,
    @StringRes placeHolder: Int

) {
    var location by remember { mutableStateOf("") }

    var isFocused by remember { mutableStateOf(selected) }
    val backgroundColor =
        if (isFocused) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface
    val borderColor = if (isFocused) tint else MaterialTheme.colorScheme.surface
    val roundedCornerShape = RoundedCornerShape(8.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(shape = roundedCornerShape, color = borderColor, width = 2.dp)
            .shadow(shape = roundedCornerShape, elevation = 3.dp)
            .background(shape = roundedCornerShape, color = backgroundColor)
    ) {
        val iconElevation = if (isFocused) 10.dp else 0.dp
        Icon(
            imageVector = Icons.Default.Brightness1,
            contentDescription = stringResource(id = placeHolder),
            tint = tint,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
                .shadow(shape = CircleShape, elevation = iconElevation)
        )
        TextField(
            value = location,
            singleLine = true,
            placeholder = { Text(stringResource(placeHolder)) },
            onValueChange = { location = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )

    }
}
