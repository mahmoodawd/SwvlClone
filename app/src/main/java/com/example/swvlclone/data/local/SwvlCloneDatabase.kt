package com.example.swvlclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.swvlclone.data.local.dao.LocationDao
import com.example.swvlclone.data.model.LocationEntity


@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SwvlCloneDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}