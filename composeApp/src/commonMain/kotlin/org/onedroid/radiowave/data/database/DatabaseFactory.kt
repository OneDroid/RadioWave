package org.onedroid.radiowave.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<RadioWaveDatabase>
}