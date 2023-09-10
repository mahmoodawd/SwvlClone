package com.example.swvlclone.usertrips.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swvlclone.R
import com.example.swvlclone.availabletrips.tripitem.components.TripModel
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import com.example.swvlclone.usertrips.details.sections.CancelTripSheetContent
import com.example.swvlclone.usertrips.details.sections.PriceAndSeatsSection
import com.example.swvlclone.usertrips.details.sections.RefundNotice
import com.example.swvlclone.usertrips.details.sections.TopSection
import com.example.swvlclone.usertrips.details.sections.TripInfoSection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TripDetailsRoute(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    tripModel: TripModel
) {
    val sheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()
    TripDetailsScreen(
        modifier = modifier,
        onBackPressed = onBackPressed,
        tripModel = tripModel,
        sheetState = sheetState,
        coroutineScope = coroutineScope
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TripDetailsScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    tripModel: TripModel,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
) {

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            CancelTripSheetContent(
                onCancelClick = { },
                onBackPressed = {
                    coroutineScope.launch { sheetState.hide() }
                }
            )
        }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState(), enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(onBackPressed = onBackPressed)
            TopSection(
                onCancelClick = {
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            TripInfoSection(
                trip = tripModel,
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            PriceAndSeatsSection()
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            RefundNotice(
                modifier = Modifier.padding(16.dp)
            ) {}
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,

    ) {
    val passAnnotatedText = buildAnnotatedString {
        val numberStyle = TextStyle(
            fontSize = 24.sp
        ).toSpanStyle()
        val textStyle = TextStyle(
            color = MaterialTheme.colorScheme.outline,
            fontWeight = FontWeight.Bold,
        ).toSpanStyle()

        withStyle(numberStyle) {
            append("12")
        }
        append(" ")
        withStyle(textStyle) {
            append(stringResource(R.string.boarding_pass).uppercase())
        }
    }
    TopAppBar(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        BackButton(onClick = onBackPressed)
        Spacer(modifier = Modifier.weight(0.5f))
        Text(text = passAnnotatedText)
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun TripDetailsPreview() {
    SwvlCloneTheme {
        TripDetailsScreen(
            tripModel =
            TripModel(
                startsAt = "09:00 AM",
                endsAt = "12:00 PM",
                timeToReachStartInMins = "30",
                timeToReachEndInMins = "60",
                startDest = "New York City",
                endDest = "Los Angeles",
            ),
            onBackPressed = {},
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
            coroutineScope = rememberCoroutineScope()
        )
    }
}