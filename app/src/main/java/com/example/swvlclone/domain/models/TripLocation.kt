package com.example.swvlclone.domain.models

import android.net.Uri
import com.google.gson.Gson

data class TripLocation(
    val from: String,
    val to: String,
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}
