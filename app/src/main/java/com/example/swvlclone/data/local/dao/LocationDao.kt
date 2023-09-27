package com.example.swvlclone.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swvlclone.data.model.LocationEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [LocationEntity] access
 */
@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationEntity: LocationEntity): Long

    @Query("SELECT * FROM location WHERE userId == :userId")
    fun getAll(userId: String): Flow<List<LocationEntity>>


    @Delete
    suspend fun delete(locationEntity: LocationEntity): Int
}