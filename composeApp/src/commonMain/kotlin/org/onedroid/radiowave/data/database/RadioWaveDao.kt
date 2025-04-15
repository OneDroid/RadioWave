package org.onedroid.radiowave.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RadioWaveDao {
    @Upsert
    suspend fun upsert(radioEntity: RadioEntity)

    @Upsert
    suspend fun upsertRecentlyUpdatedRadios(radioEntities: List<RadioEntity>)

    @Query("SELECT * FROM RadioEntity WHERE isSaved = 1")
    fun getSavedRadios(): Flow<List<RadioEntity>>

    @Query("SELECT * FROM RadioEntity")
    fun getAllRadios(): Flow<List<RadioEntity>>

    @Query("DELETE FROM RadioEntity WHERE stationuuid = :id AND isSaved = 1")
    suspend fun deleteSavedRadio(id: String)

    @Query("SELECT isSaved FROM RadioEntity WHERE stationuuid = :id")
    fun isSaved(id: String): Flow<Boolean>

    @Query("DELETE FROM RadioEntity")
    suspend fun clearAll()
}