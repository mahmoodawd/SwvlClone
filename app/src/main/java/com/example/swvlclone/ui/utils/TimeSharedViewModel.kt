package com.example.swvlclone.ui.utils

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.swvlclone.domain.models.TripTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimeSharedViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //TODO should be set to current date and time
   /* private val _sharedTime = MutableStateFlow(
        TripTime(day = "", hour = "")
    )
    val sharedTime: StateFlow<TripTime> = _sharedTime.asStateFlow()


    fun setTime(time: TripTime) {
        _sharedTime.value = time
    }*/
}