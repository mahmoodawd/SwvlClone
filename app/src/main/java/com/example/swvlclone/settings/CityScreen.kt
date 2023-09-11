package com.example.swvlclone.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canopas.campose.countrypicker.CountryPickerBottomSheet
import com.canopas.campose.countrypicker.localeToEmoji
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import java.util.Locale


@Composable
fun CityRoute(
    modifier: Modifier = Modifier, onBackPressed: () -> Unit, currentCity: String
) {
    CityScreen(
        currentCity = currentCity,
        onBackPressed = onBackPressed,
        modifier = modifier,
    )
}

@Composable
fun CityScreen(
    modifier: Modifier = Modifier,
    currentCity: String,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackButton(
            modifier = Modifier.align(Alignment.Start), onClick = onBackPressed
        )
        Text(
            text = stringResource(id = R.string.change_city),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        CityChanger(currentCountry = "Egypt") // TODO Retrieve Country from VM
        SearchCityField()
        cityList.forEach {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = it, modifier = Modifier.padding(vertical = 4.dp))
                if (it == currentCity) {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.Done, contentDescription = null)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityChanger(
    modifier: Modifier = Modifier,
    currentCountry: String
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(currentCountry) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val countryCode = getCountryCode(selectedCountry)
        Text(
            text = localeToEmoji(countryCode ?: Locale.ROOT.country),
            modifier = Modifier.padding(8.dp)
        )
        Text(text = selectedCountry)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = { openBottomSheet = true }) {
            Text(
                text = stringResource(R.string.change), color = Color.Blue
            )
        }
    }

    if (openBottomSheet) {
        CountryPickerBottomSheet(bottomSheetTitle = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(R.string.select_country_text),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
            containerColor = Color.White,
            onItemSelected = {
                selectedCountry = it.name
                openBottomSheet = false
            },
            onDismissRequest = {
                openBottomSheet = false
            })
    }
}

@Composable
fun SearchCityField(
    modifier: Modifier = Modifier
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
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
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
            label = { Text(stringResource(id = R.string.search_city)) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Filled.Clear, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CityPreview() {
    SwvlCloneTheme {
        CityScreen(
            currentCity = "Egypt",
            onBackPressed = {}
        )
    }
}


private val cityList = listOf(
    "Cairo", "Alexandria", "Fayoum"
)

fun getCountryCode(countryName: String): String? {
    val locales = Locale.getAvailableLocales()
    for (locale in locales) {
        val displayName = locale.displayCountry
        if (displayName.equals(countryName, ignoreCase = true)) {
            return locale.country
        }
    }
    return null
}
