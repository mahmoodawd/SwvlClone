package com.example.swvlclone.domain.models

import android.net.Uri
import com.google.gson.Gson

data class TripTime(
    val day: String,
    val hour: String
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}
