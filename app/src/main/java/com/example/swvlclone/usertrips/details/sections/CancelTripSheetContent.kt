package com.example.swvlclone.usertrips.details.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun CancelTripSheetContent(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.cancel_trip),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(R.string.cancellation_fee_msg),
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
        RefundNotice {}
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(
                onClick = onCancelClick,
                border = BorderStroke(color = MaterialTheme.colorScheme.primary, width = 1.dp)
            ) {
                Text(text = stringResource(id = R.string.cancel_trip).trim('?').uppercase())
            }
            TextButton(onClick = onBackPressed) {
                Text(
                    text = "Back".uppercase(),
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SheetPreview() {
    SwvlCloneTheme {
        CancelTripSheetContent(onCancelClick = { }) {}
    }
}