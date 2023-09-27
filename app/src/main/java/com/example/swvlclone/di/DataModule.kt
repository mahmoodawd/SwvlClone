package com.example.swvlclone.di

import com.example.swvlclone.data.repository.LocationRepoImpl
import com.example.swvlclone.domain.repository.LocationRepository
import com.example.swvlclone.domain.usecase.GetUserLocationsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsLocationRepository(
        locationRepository: LocationRepoImpl
    ): LocationRepository

     companion object {
         @Provides
         fun providesGetUserLocationsUseCase(locationRepo: LocationRepository) =
             GetUserLocationsUseCase(locationRepo)
     }
}
