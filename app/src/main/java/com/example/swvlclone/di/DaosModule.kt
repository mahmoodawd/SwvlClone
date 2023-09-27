package com.example.swvlclone.di

import com.example.swvlclone.data.local.SwvlCloneDatabase
import com.example.swvlclone.data.local.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesLocationDao(
        database: SwvlCloneDatabase
    ): LocationDao = database.locationDao()
}