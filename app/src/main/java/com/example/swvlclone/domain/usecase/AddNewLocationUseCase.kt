package com.example.swvlclone.domain.usecase

import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.domain.repository.LocationRepository
import javax.inject.Inject

class AddNewLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(userLocation: UserLocation): Boolean =
        locationRepository.insertLocation(userLocation)
}