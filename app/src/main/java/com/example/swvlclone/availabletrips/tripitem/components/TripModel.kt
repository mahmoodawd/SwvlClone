package com.example.swvlclone.availabletrips.tripitem.components

data class TripModel(
    val startsAt: String,
    val endsAt: String,
    val timeToReachStartInMins: String,
    val timeToReachEndInMins: String,
    val startDest: String,
    val endDest: String,
)