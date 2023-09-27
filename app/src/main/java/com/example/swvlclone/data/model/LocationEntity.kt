package com.example.swvlclone.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swvlclone.domain.models.UserLocation

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey
    val id: String,
    var userId: String? = null,
    val lat: Double,
    val lon: Double,
    val title: String,
    val type: String,
)

fun LocationEntity.asExternalModel() = UserLocation(
    title = title,
    lat = lat,
    lon = lon,
    type = type
)

