package com.example.swvlclone.availabletrips.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun HoursSection(
    modifier: Modifier = Modifier,
    hours: List<String>,
    userSelection: String = "",
    onHourSelected: (Int) -> Unit = {},
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    ScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = selectedTabIndex,
        indicator = { Box(modifier = Modifier.background(Color.Transparent)) {} }
    ) {
        hours.forEachIndexed { index, hour ->
            HourItem(
                time = hour,
                isSelected = selectedTabIndex == index,
                onClick = {
                    onHourSelected(index)
                    selectedTabIndex = index
                }
            )
        }
    }
}

@Composable
private fun HourItem(
    modifier: Modifier = Modifier,
    time: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Tab(
        selected = isSelected,
        selectedContentColor = Color.White,
        unselectedContentColor = MaterialTheme.colorScheme.outline,
        onClick = { onClick() },
        modifier = modifier
            .padding(6.dp)
            .shadow(shape = RoundedCornerShape(12.dp), elevation = 2.dp, clip = true)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White
            )

    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
            modifier = Modifier
                .padding(8.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun HoursSectionPreview() {
    SwvlCloneTheme {
        HoursSection(hours = createTimeList(), userSelection = "7 am - 8 am")
    }
}

private fun createTimeList(): List<String> {
    val timeList = mutableListOf<String>()
    for (hour in 0 until 24) {
        val startHour = hour.toString().padStart(2, '0')
        val endHour = (hour + 1).toString().padStart(2, '0')
        val isPM = hour >= 12
        timeList.add("$startHour ${if (isPM) "pm" else "am"} - $endHour ${if (isPM) "pm" else "am"}")
    }
    return timeList
}