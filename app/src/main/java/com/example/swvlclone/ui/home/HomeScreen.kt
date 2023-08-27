package com.example.swvlclone.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swvlclone.ui.home.components.BottomSection
import com.example.swvlclone.ui.home.components.FavoriteLocationSection
import com.example.swvlclone.ui.home.components.GoSection
import com.example.swvlclone.ui.home.components.OfferItem
import com.example.swvlclone.ui.home.components.TripTimeBottomSheet
import com.example.swvlclone.ui.home.components.TripsSection
import com.example.swvlclone.ui.theme.SwvlCloneTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,

        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetElevation = 4.dp,
        sheetContent = {
            TripTimeBottomSheet()
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OfferItem()
            TripsSection()
            GoSection(
                onTripTimeClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                }
            )
            FavoriteLocationSection()
            BottomSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    SwvlCloneTheme {
        HomeScreen()
    }
}