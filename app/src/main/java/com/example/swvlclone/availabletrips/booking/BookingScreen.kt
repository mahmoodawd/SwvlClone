package com.example.swvlclone.availabletrips.booking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.R
import com.example.swvlclone.availabletrips.booking.sections.MapSection
import com.example.swvlclone.availabletrips.booking.sections.PaymentTypeSection
import com.example.swvlclone.availabletrips.booking.sections.PriceSection
import com.example.swvlclone.availabletrips.booking.sections.PromoCodeSection
import com.example.swvlclone.availabletrips.booking.sections.RefundNotice
import com.example.swvlclone.availabletrips.booking.sections.SeatsSection
import com.example.swvlclone.ui.components.BackButton
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingRoute(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomSheetState =
        rememberBottomSheetScaffoldState(
            bottomSheetState = SheetState(skipHiddenState = false, skipPartiallyExpanded = false)
        )
    val coroutineScope = rememberCoroutineScope()
    BookingScreen(
        onBackPressed = onBackPressed,
        scaffoldState = bottomSheetState,
        coroutineScope = coroutineScope,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
) {

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetSwipeEnabled = false,
        sheetShape = RectangleShape,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetDragHandle = { Box {} },
        sheetContent = {
            SuccessfulBookingSheetContent(
                onBookReturnClick = { },
                onViewTripDetailsClick = {},
                onClose = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                })
        })
    {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                MapSection()
                BackButton(
                    onClick = onBackPressed, modifier = Modifier
                        .padding(start = 16.dp, top = 32.dp)
                        .align(Alignment.TopStart)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    PromoCodeSection(onclick = { })
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        PaymentTypeSection(onclick = {}, modifier = Modifier.weight(1f))
                        SeatsSection(modifier = Modifier.weight(1f))
                    }
                    PriceSection()
                    RefundNotice(onclick = {})
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .padding(top = 16.dp),
                        onClick = { coroutineScope.launch { scaffoldState.bottomSheetState.expand() } }
                    ) {
                        Text(text = stringResource(id = R.string.book))

                    }


                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BookingScreenPreview() {
    SwvlCloneTheme {
        BookingScreen(
            onBackPressed = { /*TODO*/ },
            scaffoldState = rememberBottomSheetScaffoldState(),
            coroutineScope = rememberCoroutineScope()
        )
    }
}