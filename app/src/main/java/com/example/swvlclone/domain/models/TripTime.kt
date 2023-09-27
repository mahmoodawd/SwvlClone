package com.example.swvlclone.domain.models

import android.net.Uri
import com.google.gson.Gson

data class TripTime(
    val day: Long,
    val hour: Int
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}
