package com.example.swvlclone.ui.utils

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.swvlclone.domain.models.TripLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationSharedViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _sharedLocation = MutableStateFlow(TripLocation(from = "", to = ""))
    val sharedLocation = _sharedLocation.asStateFlow()

    fun setLocation(location: TripLocation) {
        _sharedLocation.value = location

    }
}