package com.example.swvlclone.domain.usecase

import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.domain.models.UserLocationsResult
import com.example.swvlclone.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(): Flow<UserLocationsResult> = locationRepository.getAll()


}