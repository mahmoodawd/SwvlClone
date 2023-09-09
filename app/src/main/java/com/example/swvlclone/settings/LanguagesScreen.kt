package com.example.swvlclone.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.SwvlCloneTopBar
import com.example.swvlclone.ui.theme.SwvlCloneTheme


@Composable
fun LanguagesRoute(
    modifier: Modifier = Modifier,
    currentLanguage: String,
    onBackPressed: () -> Unit
) {
    LanguagesScreen(
        currentLanguage = currentLanguage,
        onBackPressed = onBackPressed
    )
}

@Composable
fun LanguagesScreen(
    modifier: Modifier = Modifier,
    currentLanguage: String,
    onBackPressed: () -> Unit,
    onLanguageClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SwvlCloneTopBar(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(R.string.language),
            icon = R.drawable.arrow_back,
            onIconClick = onBackPressed
        )
        languageList.forEach {
            Row {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { onLanguageClick() })
                if (it == currentLanguage) {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.Done, contentDescription = null)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LanguagesPreview() {
    SwvlCloneTheme {
        LanguagesScreen(currentLanguage = "English", onBackPressed = {})
    }
}

private val languageList = listOf(
    "English",
    "Deutsch",
    "Français",
    "Italiano",
    "العربية (مصر)",
    "العربية (الأردن)",
    "العربية (المملكة العربية السعودية)",
    "اردو",
    "Português",
    "Español (Latinoamérica)",
    "Kiswahili",
    "Español (España)",
)