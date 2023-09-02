package com.example.swvlclone.availabletrips.sections

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun DaysSection(
    modifier: Modifier = Modifier,
    days: List<String>,
    userSelection: String = "",
    onDaySelected: (Int) -> Unit = {}
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    ScrollableTabRow(
        modifier = modifier,
        containerColor = Color.White,
        edgePadding = 2.dp,
        selectedTabIndex = selectedTabIndex,
    ) {
        days.forEachIndexed { index, day ->
            DayItem(
                time = day,
                isSelected = selectedTabIndex == index,
                onClick = {
                    onDaySelected(index)
                    selectedTabIndex = index
                }
            )
        }
    }
}

@Composable
private fun DayItem(
    modifier: Modifier = Modifier,
    time: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Tab(
        selected = isSelected,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.outline,
        onClick = { onClick() },
        modifier = modifier
            .padding(6.dp)

    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DaysSectionPreview() {
    SwvlCloneTheme {
        DaysSection(days = createDaysList(), userSelection = "Thur, 24 Aug")
    }
}

private fun createDaysList(): List<String> {
    return listOf(
        "Tomorrow",
        "Wed, 23 Aug",
        "Thur, 24 Aug",
        "Fir, 25 Aug",
        "Sat, 26 Aug"
    )
}