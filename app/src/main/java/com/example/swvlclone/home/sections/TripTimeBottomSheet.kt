package com.example.swvlclone.home.sections

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.comon.utils.extractDateFormatted
import com.example.swvlclone.comon.utils.extractHourFormatted
import com.example.swvlclone.domain.models.TripTime
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripTimeBottomSheet(
    modifier: Modifier = Modifier,
    onProceedButtonClick: (TripTime) -> Unit = {}
) {

    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    val timePickerState = rememberTimePickerState(initialHour = calendar.get(Calendar.HOUR_OF_DAY))
    calendar.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
    calendar.set(Calendar.MINUTE, timePickerState.minute)
    calendar.isLenient = false

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(R.string.show_trips_starting_from),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        Divider()
        DateTimePickerItem(
            text = R.string.time,
            pickedValue = extractHourFormatted(calendar.timeInMillis),
            dialogPickerContent = {
                TimePicker(
                    state = timePickerState,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            },
        )
        Divider()
        DateTimePickerItem(
            text = R.string.date,
            pickedValue = extractDateFormatted(datePickerState.selectedDateMillis!!),
            dialogPickerContent = {
                DatePicker(state = datePickerState, showModeToggle = false)
            },
        )
        Divider()
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            onClick = {
                onProceedButtonClick(
                    TripTime(
                        day = datePickerState.selectedDateMillis!!,
                        hour = timePickerState.hour
                    )
                )
            }) {
            Text(text = "Proceed".uppercase())

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateTimePickerItem(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    pickedValue: String = "Now",
    dialogPickerContent: @Composable (ColumnScope.() -> Unit),
) {
    var showPickerDialog by remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Text(
            text = stringResource(id = text),
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = pickedValue,
            modifier = Modifier.clickable {
                showPickerDialog = true
            }
        )

        if (showPickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showPickerDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showPickerDialog = false
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showPickerDialog = false
                    }) {
                        Text(text = "Cancel")
                    }
                },
                content = dialogPickerContent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripTimeSheetPreview() {
    SwvlCloneTheme {
        TripTimeBottomSheet()
    }
}