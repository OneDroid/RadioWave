package org.onedroid.radiowave.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<RadioWaveDatabase> {
    override fun initialize(): RadioWaveDatabase
}