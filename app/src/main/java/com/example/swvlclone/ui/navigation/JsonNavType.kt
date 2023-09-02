package com.example.swvlclone.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.swvlclone.domain.models.TripLocation
import com.example.swvlclone.domain.models.TripTime
import com.google.gson.Gson

abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}

class TimeArgType : JsonNavType<TripTime>() {
    override fun fromJsonParse(value: String): TripTime =
        Gson().fromJson(value, TripTime::class.java)

    override fun TripTime.getJsonParse(): String =
        Gson().toJson(this)
}


class LocationArgType : JsonNavType<TripLocation>() {
    override fun fromJsonParse(value: String): TripLocation =

        Gson().fromJson(value, TripLocation::class.java)

    override fun TripLocation.getJsonParse(): String =
        Gson().toJson(this)
}
