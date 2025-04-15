package org.onedroid.radiowave.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        RadioEntity::class
    ],
    version = 1
)

@TypeConverters(TypeConverter::class)

@ConstructedBy(BookDatabaseConstructor::class)

abstract class RadioWaveDatabase : RoomDatabase() {
    abstract val radioWaveDao: RadioWaveDao

    companion object {
        const val RADIO_WAVE_DB_NAME = "RadioWave.db"
    }

    suspend fun clearAllEntities() {
        radioWaveDao.clearAll()
    }
}