package com.example.swvlclone.packages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun PackageRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    PackagesScreen(
        onBackPressed = onBackPressed,
        modifier = modifier,
    )
}

@Composable
fun PackagesScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BackButton(
            modifier = Modifier.align(Alignment.Start),
            onClick = onBackPressed
        )
        Text(
            text = stringResource(id = R.string.packages),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        packageItemLists.forEach {
            PackageItemInfo(
                title = it.name,
                currentPrice = it.currentPrice,
                oldPrice = it.oldPrice,
                numberOfRides = it.numberOfRides,
                maxBookingPerDay = it.maxBookingPerDay,
                maxDiscount = it.maxDiscount,
                validationDays = it.validationDays,
            )
        }
    }
}

@Composable
fun PackageItemInfo(
    modifier: Modifier = Modifier,
    title: String,
    currentPrice: Int,
    oldPrice: Int,
    numberOfRides: Int,
    maxBookingPerDay: Int,
    maxDiscount: Int,
    validationDays: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
    ) {
        val priceAnnotatedString = buildAnnotatedString {
            withStyle(
                MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.outline,

                    letterSpacing = 2.sp,
                ).toSpanStyle()
            ) {
                append("EGP ")
            }
            withStyle(
                MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.scrim,
                    letterSpacing = 1.sp,
                ).toSpanStyle()
            ) {
                append(currentPrice.toString())
            }
            withStyle(
                MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.outline,
                ).toSpanStyle()
            ) {
                append("/$numberOfRides Trips")
            }
        }
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = priceAnnotatedString)
        Text(text = "valid for $validationDays days")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = """✔️ Enjoy $numberOfRides rides package and pay EGP $currentPrice
                |instead of EGP $oldPrice (Limited time offer & valid
                |for all rides) """.trimMargin(),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = """✔️ Get this package and get 100% off on every
                |ride with a maximum discount of $maxDiscount EGP per
                |ride. """.trimMargin(),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "✔️ Up to $maxBookingPerDay booking per day",
            modifier = Modifier.padding(8.dp)
        )

    }
}

data class PackageItem(
    val name: String,
    val numberOfRides: Int,
    val currentPrice: Int,
    val oldPrice: Int,
    val maxDiscount: Int,
    val maxBookingPerDay: Int,
    val validationDays: Int,
)

private val packageItemLists = listOf(
    PackageItem(
        name = "Extra Ride🚀",
        numberOfRides = 15,
        currentPrice = 630,
        oldPrice = 840,
        maxDiscount = 56,
        maxBookingPerDay = 4,
        validationDays = 30,
    ),
    PackageItem(
        name = "Extra Saving💰",
        numberOfRides = 5,
        currentPrice = 400,
        oldPrice = 500,
        maxDiscount = 100,
        maxBookingPerDay = 4,
        validationDays = 30,
    )
)

@Preview(showBackground = true)
@Composable
private fun PackagesPreview() {
    SwvlCloneTheme {
        PackagesScreen {}
    }
}