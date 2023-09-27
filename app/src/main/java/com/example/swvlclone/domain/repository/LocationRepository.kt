package com.example.swvlclone.domain.repository

import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.domain.models.UserLocationsResult
import kotlinx.coroutines.flow.Flow

/**
 ** Domain layer Interface for user Locations
 */
interface LocationRepository {

    suspend fun getAll(): Flow<UserLocationsResult>

    suspend fun insertLocation(userLocation: UserLocation): Boolean

    suspend fun deleteLocation(userLocation: UserLocation): Boolean
}