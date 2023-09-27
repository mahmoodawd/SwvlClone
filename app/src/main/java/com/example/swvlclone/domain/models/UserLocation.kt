package com.example.swvlclone.domain.models

import com.example.swvlclone.data.model.LocationEntity

data class UserLocation(
    val lat: Double,
    val lon: Double,
    val title: String,
    val type: String,
)

fun UserLocation.asEntity() =
    LocationEntity(
        id = hashCode().toString(),
        lat = lat,
        lon = lon,
        title = title,
        type = type
    )

fun UserLocation.asFireStoreEntity(uid: String) =
    hashMapOf(
        "id" to hashCode(),
        "uid" to uid,
        "lat" to lat,
        "lon" to lon,
        "title" to title,
        "type" to type
    )
