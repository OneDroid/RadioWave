package org.onedroid.radiowave.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<RadioWaveDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(RadioWaveDatabase.RADIO_WAVE_DB_NAME)
        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}