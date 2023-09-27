package com.example.swvlclone.comon.utils

import com.example.swvlclone.domain.models.TripTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun extractHourFormatted(date: Long): String {
    val timeFormatter = SimpleDateFormat("hh:mm a", Locale.ROOT)
    return timeFormatter.format(date)
}


fun extractDateFormatted(date: Long): String {
    val dateFormatter = SimpleDateFormat("EEE dd MMM", Locale.ROOT)
    return dateFormatter.format(date)
}

fun extractFullDateTime(date: TripTime): String {
    return if (date.isNow()) {
        "Now"
    } else {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, date.hour.toInt())
        val timeFormatter = SimpleDateFormat("hh a", Locale.ROOT)
        val dateFormatter = SimpleDateFormat("EEE dd MMM", Locale.ROOT)
        val time = timeFormatter.format(calendar.timeInMillis)
        val day = if (date.isToday()) {
            "Today"
        } else dateFormatter.format(date.day)
        "$day at $time"
    }
}

fun TripTime.isNow(): Boolean {
    val currentInMillis = this.day
    val currentDate = Date(currentInMillis)
    val today = Date()

    val currentCalendar = Calendar.getInstance().apply { time = currentDate }
    val todayCalendar = Calendar.getInstance().apply { time = today }

    return currentCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)
            && currentCalendar.get(Calendar.DAY_OF_YEAR) == todayCalendar.get(Calendar.DAY_OF_YEAR)
            && currentCalendar.get(Calendar.HOUR_OF_DAY) == todayCalendar.get(Calendar.HOUR_OF_DAY)
}

fun TripTime.isToday(): Boolean {
    val currentInMillis = this.day
    val currentDate = Date(currentInMillis)
    val today = Date()

    val currentCalendar = Calendar.getInstance().apply { time = currentDate }
    val todayCalendar = Calendar.getInstance().apply { time = today }

    return currentCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)
            && currentCalendar.get(Calendar.DAY_OF_YEAR) == todayCalendar.get(Calendar.DAY_OF_YEAR)
}