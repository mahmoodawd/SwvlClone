package com.example.swvlclone.data.repository

import com.example.swvlclone.data.local.dao.LocationDao
import com.example.swvlclone.data.model.LocationEntity
import com.example.swvlclone.data.model.asExternalModel
import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.domain.models.UserLocationsResult
import com.example.swvlclone.domain.models.asEntity
import com.example.swvlclone.domain.models.asFireStoreEntity
import com.example.swvlclone.domain.repository.LocationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.concurrent.CancellationException
import javax.inject.Inject

/**
 *   Data layer implementation for user Locations
 */

const val USER_LOCATIONS_COLLECTION = "user_locations"

class LocationRepoImpl @Inject constructor(
    private val locationDao: LocationDao,
    firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : LocationRepository {
    private val uid = firebaseAuth.currentUser!!.uid
    override suspend fun getAll(): Flow<UserLocationsResult> {
        updateCachedLocations()
        return locationDao.getAll(uid).map { locations ->
            Timber.d("locations for $uid: $locations")
            UserLocationsResult(locations.map { it.asExternalModel() })
        }
    }

    private suspend fun updateCachedLocations() {
        try {
            val collection = firestore.collection(USER_LOCATIONS_COLLECTION)
                .get().await()
            //TODO clean data from local
            collection.documents.forEach { snapshot ->
                snapshot.data?.let {
                    locationDao.insertLocation(it.asLocationEntity())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Timber.e(" Error adding to local ${e.message}")
        }
    }

    override suspend fun insertLocation(userLocation: UserLocation): Boolean {

        val task = firestore.collection(USER_LOCATIONS_COLLECTION)
            .add(userLocation.asFireStoreEntity(uid))
            .addOnSuccessListener { Timber.i("User location Inserted Successfully to Firebase with uid: $uid") }
            .addOnFailureListener { Timber.e(" Error adding to Firebase ${it.message}") }

        return task.isSuccessful
    }


    override suspend fun deleteLocation(userLocation: UserLocation): Boolean =

        //TODO Delete from fireStore
        locationDao.delete(userLocation.asEntity()) > 0
}

private fun Map<String, Any>.asLocationEntity(): LocationEntity {
    return LocationEntity(
        id = get("id").toString(),
        userId = get("uid").toString(),
        lat = get("lat") as Double,
        lon = get("lon") as Double,
        type = get("type").toString(),
        title = get("title").toString(),
    )
}

