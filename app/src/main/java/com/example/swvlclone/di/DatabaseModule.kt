package com.example.swvlclone.di

import android.content.Context
import androidx.room.Room
import com.example.swvlclone.data.local.SwvlCloneDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesSwvlCloneDatabase(
        @ApplicationContext context: Context,
    ): SwvlCloneDatabase =
        Room.databaseBuilder(
            context,
            SwvlCloneDatabase::class.java,
            "swvl-clone-db"
        ).build()


}